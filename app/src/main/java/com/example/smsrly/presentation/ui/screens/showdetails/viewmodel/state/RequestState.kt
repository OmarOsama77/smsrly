package com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state

sealed class RequestState{
    object Idle : RequestState()
    object Loading: RequestState()
    data class Success(
        val message : String
    ): RequestState()
    data class Failed(
        val message: String
    ): RequestState()
}