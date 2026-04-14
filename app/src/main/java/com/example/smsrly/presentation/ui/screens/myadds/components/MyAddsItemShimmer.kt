package com.example.smsrly.presentation.ui.screens.myadds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun MyAddsItemShimmer() {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    Box(
        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .shimmer(shimmer)
            .background(color = Color.LightGray)
    )
}