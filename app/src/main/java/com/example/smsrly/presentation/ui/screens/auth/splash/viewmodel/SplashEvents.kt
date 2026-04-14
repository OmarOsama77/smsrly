package com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel

sealed class SplashEvents{
    object Success: SplashEvents()
    object Failed : SplashEvents()
}