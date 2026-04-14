package com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel

sealed class SplashState{
    data object Idle: SplashState()
    data object Loading: SplashState()
    data object Success : SplashState()
    data object Failed : SplashState()
}