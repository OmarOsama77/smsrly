package com.example.smsrly.presentation.ui.screens.sell.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel

@Composable
fun AddsHeader(modifier: Modifier = Modifier,viewModel: SellViewModel) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Smsrly",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.height(20.dp))
    AddImagesBox(modifier,viewModel)
}