package com.example.smsrly.presentation.ui.screens.chats

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.chats.components.ChatCard
import com.example.smsrly.presentation.ui.screens.chats.viewmodel.ChatsViewModel
import com.example.smsrly.presentation.ui.screens.chats.viewmodel.states.ChatsState
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.google.firebase.database.FirebaseDatabase
import kotlinx.serialization.Serializable

@Serializable
data object ChatsRoute

@Composable
fun Chats(navController: NavController) {
    val viewModel: ChatsViewModel = hiltViewModel()
    val user = viewModel.user.collectAsState()
    val conversations = viewModel.conversations.collectAsState()
    val state = viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier
            .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Chats", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                UserImage(user.value?.imageUrl)
            }
        }


        when (state.value) {
            is ChatsState.Idle -> {
                item { CircularProgressIndicator() }
            }

            is ChatsState.Loading -> {
                item { CircularProgressIndicator() }
            }

            is ChatsState.Success -> {
                items(conversations.value.size) { index ->

                    ChatCard(
                        navController,
                        conversations.value[index].receiverInfo!!,
                        conversations.value[index].lastMessage
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(top = 12.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )
                }
            }

            is ChatsState.Failed -> {
                item { CircularProgressIndicator() }
            }

        }

    }
}