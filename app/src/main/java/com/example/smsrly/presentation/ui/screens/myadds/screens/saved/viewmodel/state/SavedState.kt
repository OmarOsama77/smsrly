package com.example.smsrly.presentation.ui.screens.myadds.screens.saved.viewmodel.state

sealed class SavedState{
    object Idle : SavedState()
    object Loading : SavedState()
    object Success : SavedState()
    data class Failed(
        val message : String
    ) : SavedState()
}