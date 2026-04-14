package com.example.smsrly.presentation.ui.screens.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.smsrly.R

@Composable
fun UserImage(imageUrl: String?) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(40.dp))

    ) {
        if (imageUrl == null) {
            Image(
                painter = painterResource(R.drawable.anonymous),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(90.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = imageUrl
            )
        }
    }
}