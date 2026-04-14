package com.example.smsrly.presentation.ui.screens.home.viewmodel.states

sealed class NearestRealEstateState{
    object Idle : NearestRealEstateState()
    object Loading : NearestRealEstateState()
    object Success: NearestRealEstateState()
    data class Failed(
        val message:String
    ): NearestRealEstateState()
}