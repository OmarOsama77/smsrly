package com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel

sealed class OtpState{
    object Idle : OtpState()
    object Loading: OtpState()
    object  Success:OtpState()
    object  Failed : OtpState()

}