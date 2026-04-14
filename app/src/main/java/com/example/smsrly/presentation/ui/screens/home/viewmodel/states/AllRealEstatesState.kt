package com.example.smsrly.presentation.ui.screens.home.viewmodel.states

sealed class AllRealEstatesState{
    object Idle: AllRealEstatesState()
    object Loading: AllRealEstatesState()
    object Success: AllRealEstatesState()
    data class Failed(
        val message:String
    ) : AllRealEstatesState()
}