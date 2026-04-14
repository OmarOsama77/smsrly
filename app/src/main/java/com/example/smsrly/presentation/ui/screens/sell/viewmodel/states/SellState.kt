package com.example.smsrly.presentation.ui.screens.sell.viewmodel.states

sealed class SellState{
    data object Idle: SellState()
    data object Loading: SellState()
    data object Success : SellState()
    data object Failed : SellState()
}