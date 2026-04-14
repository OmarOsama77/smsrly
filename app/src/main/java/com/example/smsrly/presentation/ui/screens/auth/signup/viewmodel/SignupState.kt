package com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel

sealed class SignupState {
    object Idle : SignupState()
    object Loading : SignupState()
    object Success : SignupState()
    object Failed : SignupState()

}