package com.example.smsrly.presentation.ui.screens.chats.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.presentation.ui.screens.chats.conversation.ConversationRoute
import com.example.smsrly.presentation.ui.screens.components.UserImage

@Composable
fun ChatCard(navController: NavController,userInfo: UserInfo,lastMessage :String) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable{
            navController.navigate(ConversationRoute(userInfo))
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        UserImage(userInfo.userImage)
        Spacer(modifier = Modifier.width(15.dp))
        Column() {
            Text(userInfo.userName, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(lastMessage)
        }

    }

}