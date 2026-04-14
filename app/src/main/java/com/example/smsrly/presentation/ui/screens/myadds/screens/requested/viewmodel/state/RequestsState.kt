package com.example.smsrly.presentation.ui.screens.myadds.screens.requested.viewmodel.state

import com.example.smsrly.domain.models.RealEstate

sealed class RequestsState{
    object Idle : RequestsState()
    object Loading : RequestsState()
    object Success: RequestsState()
    data class Failed(
        val message : String
    ):RequestsState()
}