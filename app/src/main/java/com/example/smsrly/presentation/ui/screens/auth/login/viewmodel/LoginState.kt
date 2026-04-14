package com.example.smsrly.presentation.ui.screens.auth.login.viewmodel

sealed class LoginState {
    object Ideal : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    object Failed : LoginState()
}