package com.example.smsrly.presentation.ui.screens.chats.conversation.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smsrly.R

@Composable
fun ConversationBottomBar(onSend:()->Unit,txt:String,onValueChange:(String)->Unit) {
    val message = remember{ mutableStateOf("") }
    Row(
        modifier = Modifier
            .imePadding()
            .padding(start = 10.dp, bottom = 15.dp)
    ) {
        ConversationTextField(
            txt = txt,
            onValueChange = { onValueChange(it) }
        )
        Spacer(Modifier.width(10.dp))
        IconButton({
                onSend()
        }) {
            Icon(
                painter = painterResource(R.drawable.dm),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                tint = Color.White
            )
        }
    }
}