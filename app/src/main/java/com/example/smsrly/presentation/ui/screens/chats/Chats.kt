package com.example.smsrly.presentation.ui.screens.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.components.UserImage
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod
import kotlinx.serialization.Serializable

@Serializable
data object ChatsRoute

@Composable
fun Chats(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Chats", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                UserImage(null)
            }
        }
        items(12){
            ChatCard()
            HorizontalDivider(modifier = Modifier.padding(top = 12.dp),color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        }

    }
}