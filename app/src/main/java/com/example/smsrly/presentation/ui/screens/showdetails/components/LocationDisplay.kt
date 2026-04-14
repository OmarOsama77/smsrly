package com.example.smsrly.presentation.ui.screens.showdetails.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import com.example.smsrly.R

@Composable
fun LocationDisplay(longitude: Double, latitude: Double) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val uri =
                    ("geo:${latitude},${longitude}" +
                            "?q=${latitude},${longitude}").toUri()


                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.location),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}