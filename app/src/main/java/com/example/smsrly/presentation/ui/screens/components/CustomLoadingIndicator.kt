package com.example.smsrly.presentation.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun CustomLoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            color = Primary
        )
    }
}