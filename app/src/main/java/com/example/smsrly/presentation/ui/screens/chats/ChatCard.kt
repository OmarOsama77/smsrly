package com.example.smsrly.presentation.ui.screens.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smsrly.presentation.ui.screens.components.UserImage

@Composable
fun ChatCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        UserImage("https://media.gettyimages.com/id/2213939595/photo/al-nassr-v-al-ittihad-saudi-pro-league.jpg?s=1024x1024&w=gi&k=20&c=JEhY9mDwysyyY8QP0n1r32GpvYj6Mk9fDrLgTYFdfZ0=")
        Spacer(modifier = Modifier.width(15.dp))
        Column() {
            Text("Cristiano Ronaldo", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text("My last message is ")
        }

    }

}