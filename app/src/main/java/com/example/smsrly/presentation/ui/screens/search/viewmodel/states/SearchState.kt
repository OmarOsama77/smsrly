package com.example.smsrly.presentation.ui.screens.search.viewmodel.states

import com.example.smsrly.domain.models.RealEstate

sealed class SearchState{
    object Idle : SearchState()
    object Loading : SearchState()
    object Success : SearchState()
    data class Failed (
        val message:String
    ): SearchState()
}