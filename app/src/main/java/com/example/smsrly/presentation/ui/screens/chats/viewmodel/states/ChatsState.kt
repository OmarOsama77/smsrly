package com.example.smsrly.presentation.ui.screens.chats.viewmodel.states

sealed class ChatsState{
    object Idle: ChatsState()
    object Loading : ChatsState()
    object Success : ChatsState()
    object Failed : ChatsState()
}