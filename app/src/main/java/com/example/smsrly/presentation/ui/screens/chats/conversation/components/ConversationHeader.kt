package com.example.smsrly.presentation.ui.screens.chats.conversation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smsrly.R
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun ConversationHeader(navController: NavController,receiverData: UserInfo) {
    Box(
        modifier = Modifier
            .background(color = Primary)
            .fillMaxWidth()
            .height(90.dp)
    ) {

        Row(
            modifier = Modifier.padding(top = 35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton({
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(10.dp))
            UserImage(receiverData.userImage)
            Spacer(Modifier.width(10.dp))
            Text(receiverData.userName, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)

        }
    }
}