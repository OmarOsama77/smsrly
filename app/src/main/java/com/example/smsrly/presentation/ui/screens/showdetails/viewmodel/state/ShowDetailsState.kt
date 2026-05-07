package com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state

sealed class ShowDetailsState {
    data object Idle: ShowDetailsState()
    data object Loading: ShowDetailsState()
    data object Success: ShowDetailsState()
    data object Failed : ShowDetailsState()
}