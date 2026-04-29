package com.example.smsrly.presentation.ui.screens.nointernet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.R


@Composable
fun NoInternet(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.nowifi),
            contentDescription = null,
            Modifier.size(90.dp)
        )
        Spacer(Modifier.height(20.dp))
        Text("Ooops!", fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 22.sp)
        Spacer(Modifier.height(20.dp))
        Text("No intent connection found",fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 22.sp)
        Spacer(Modifier.height(5.dp))
        Text("Check your connection",fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 22.sp)
    }

}