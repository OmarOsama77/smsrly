package com.example.smsrly.presentation.ui.screens.showdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ShowDetailsItem(painter : Painter,count: String) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(25.dp)
    )
    Spacer(Modifier.width(5.dp))
    Text(count)
}