package com.example.smsrly.presentation.ui.screens.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.R

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .height(300.dp),
        painter = painterResource(R.drawable.logo),
        contentDescription = null
    )
    Text("Login", fontSize = 30.sp, fontWeight = FontWeight.Bold)
}