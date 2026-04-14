package com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel

sealed class OtpEvent{
    data class Success(val message:String) : OtpEvent()
    data class Failed (val message: String) : OtpEvent()
}