package com.example.smsrly.presentation.ui.screens.chats.conversation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun Message(color: Color) {
    Box(
        modifier = Modifier

            .clip(RoundedCornerShape(12.dp))
            .background(color = color)
            .padding(horizontal = 12.dp, vertical = 8.dp),


    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Text("fdshoifhdsoifhdsohfoidshfiodshfdsohfdsiohfdsoidkfghgsjkdhgjkds n fdshoifhdsoifhdsohfoidshfiodshfdsohfdsiohfdsoig", fontSize = 18.sp, color = Color.Black)

        }
    }
}