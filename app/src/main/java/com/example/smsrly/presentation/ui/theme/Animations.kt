package com.example.smsrly.presentation.ui.theme

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

val slideInFromRight = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(600))
val slideOutToLeft = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(600))
val slideInFromLeft = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(600))
val slideOutToRight = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(600))