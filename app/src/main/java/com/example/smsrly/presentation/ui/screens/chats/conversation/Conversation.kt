package com.example.smsrly.presentation.ui.screens.chats.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.ConversationBottomBar
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.ConversationHeader
import com.example.smsrly.presentation.ui.screens.chats.conversation.components.Message
import com.example.smsrly.presentation.ui.screens.chats.conversation.viewmodel.ConversationViewModel
import com.example.smsrly.presentation.ui.theme.Primary
import com.example.smsrly.utility.UserInfoNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class ConversationRoute(val receiverData : UserInfo)

fun NavGraphBuilder.conversation(navController: NavController) {
    composable<ConversationRoute>(typeMap = mapOf(
        typeOf<UserInfo>() to UserInfoNavType
    ) ) { backStackEntry ->
        val rout = backStackEntry.toRoute<ConversationRoute>()

        Conversation(navController,rout.receiverData)
    }
}

@Composable
fun Conversation(navController: NavController,receiverData : UserInfo) {
    val viewModel: ConversationViewModel = hiltViewModel()

    val text = remember { mutableStateOf("") }
    val conversation = viewModel.conversation.collectAsState()
    val user = viewModel.user.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.readAConversation(user.value!!.userId!!,receiverData.userId)
    }

    LaunchedEffect(conversation.value.size) {
        val lastIndex = (conversation.value.size) - 1
        if (lastIndex >= 0) {
            listState.animateScrollToItem(lastIndex)
        }
    }

    Scaffold(
        Modifier.imePadding(),
        topBar = {
            ConversationHeader(navController,receiverData)
        },
        bottomBar = {
            ConversationBottomBar({
                viewModel.sendMessage(receiverData.userId,text.value)
                text.value = ""
            },text.value,) {
                text.value = it
            }
        }
    ) {innerPadding->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .imePadding(),

        ) {
            items(conversation.value.size){index->
                if(conversation.value[index].senderId==user.value!!.userId){
                    Message(Primary,conversation.value[index].text, horizontalAr = Arrangement.End)
                }else{
                    Message(Color.Gray.copy(.2f),conversation.value[index].text, horizontalAr = Arrangement.Start)
                }

            }
        }

    }


}
