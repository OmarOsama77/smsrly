package com.example.smsrly.presentation.ui.screens.chats.conversation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smsrly.R

@Composable
fun ConversationBottomBar(onSend:()->Unit,txt:String,onValueChange:(String)->Unit) {
   Row(
       modifier = Modifier.fillMaxWidth()
           .padding(bottom = 10.dp),
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement = Arrangement.SpaceAround
   ) {
        ConversationTextField(txt) {
            onValueChange(it)
        }
       IconButton({onSend()}) {
           Icon(painterResource(R.drawable.dm), modifier = Modifier.size(35.dp), contentDescription = "")
       }
   }
}