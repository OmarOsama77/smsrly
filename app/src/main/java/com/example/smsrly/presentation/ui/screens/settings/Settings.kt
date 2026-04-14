package com.example.smsrly.presentation.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.settings.components.SettingsBody
import com.example.smsrly.presentation.ui.screens.settings.components.SettingsUpperBox
import com.example.smsrly.presentation.ui.screens.settings.viewmodel.SettingsViewModel
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute
@Composable
fun Settings(navController: NavController) {
    val viewModel : SettingsViewModel = hiltViewModel()
    Box {

        SettingsUpperBox()
        SettingsBody(navController,viewModel)

    }
}