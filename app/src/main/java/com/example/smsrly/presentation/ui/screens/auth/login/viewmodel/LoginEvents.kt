package com.example.smsrly.presentation.ui.screens.auth.login.viewmodel

sealed class LoginEvents {
    data object Success: LoginEvents()
    data class Failed(val message: String) : LoginEvents()
}