package com.example.smsrly.presentation.ui.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.home.components.Item

@Composable
fun SearchBody() {
    Text("Results", fontSize = 22.sp, color = Color(0xFF737373))
    Spacer(Modifier.height(10.dp))

}