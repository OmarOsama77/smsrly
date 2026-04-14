package com.example.smsrly.presentation.ui.screens.home.viewmodel.states

sealed class UserState{
    data object Idle : UserState()
    data object Loading : UserState()
    data object Success: UserState()
    data class  Failed(val message:String): UserState()
}