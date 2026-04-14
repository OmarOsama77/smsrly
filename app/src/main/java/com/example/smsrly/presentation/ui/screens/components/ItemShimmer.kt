package com.example.smsrly.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ItemShimmer() {
    val shimmer = rememberShimmer(ShimmerBounds.View)
    Box(
        modifier = Modifier
            .height(109.dp)
            .fillMaxWidth()
            .shimmer(shimmer)
            .background(Color.LightGray,  shape = RoundedCornerShape(12.dp))
    )
}