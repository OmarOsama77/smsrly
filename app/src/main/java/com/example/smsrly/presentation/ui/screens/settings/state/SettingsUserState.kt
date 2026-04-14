package com.example.smsrly.presentation.ui.screens.settings.state

sealed class SettingsUserState{
    object Idle: SettingsUserState()
    object Loading: SettingsUserState()
    object Success : SettingsUserState()
    object Failed : SettingsUserState()
    object LoggedOut: SettingsUserState()
    data class AccountDeleted(
        val message: String
    ): SettingsUserState()
}