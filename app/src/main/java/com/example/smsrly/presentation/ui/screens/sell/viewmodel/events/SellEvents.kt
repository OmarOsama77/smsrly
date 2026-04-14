package com.example.smsrly.presentation.ui.screens.sell.viewmodel.events

sealed class SellEvents{
    data class ShowToast(val message:String) : SellEvents()
}