package com.example.smsrly.presentation.ui.screens.chats.conversation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.ConversationBottomBar
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.ConversationHeader
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.Message
import com.example.smsrly.presentation.ui.screens.chats.conversation.viewmodel.ConversationViewModel
import com.example.smsrly.presentation.ui.theme.Primary
import kotlinx.serialization.Serializable

@Serializable
data class ConversationRoute(val receiverId:Int)

fun NavGraphBuilder.conversation(navController: NavController){
    composable<ConversationRoute>{backStackEntry->
        val receiverId = backStackEntry.arguments?.getInt("receiverId")
        Log.d("The omar ",backStackEntry.arguments.toString())
        Log.d("The omar2 ",receiverId.toString())
        Conversation(navController,receiverId)
    }
}
@Composable
fun Conversation(navController: NavController,receiverId:Int?) {
    val viewModel: ConversationViewModel = hiltViewModel()
    val text = remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFF0B141A),
        topBar = {
            ConversationHeader()
        },
        bottomBar = {
            ConversationBottomBar(onSend = {
              viewModel.sendMessage(receiverId!!,text.value)
            },text.value,{
                text.value  = it
            })
        }
    ) { _ ->

        LazyColumn(
            modifier = Modifier
                .padding(top = 95.dp, start = 5.dp, end = 5.dp, bottom = 90.dp)
                .fillMaxWidth()
                .imePadding()


        ) {
            items(0) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = if (select[index]) Arrangement.End else Arrangement.Start
                ) {
//                    if (select[index]) {
                    Message(Color(0xFF005C4B))

//                    } else {
//                        Message(Color(0xFF1F2C34))
//                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}