package com.example.smsrly.presentation.ui.screens.auth.login

import OtpRoute
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.auth.login.components.LoginBottom
import com.example.smsrly.presentation.ui.screens.auth.login.components.LoginForm
import com.example.smsrly.presentation.ui.screens.auth.login.components.LoginHeader
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Composable
fun Login(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .imePadding()
            .padding(top = 40.dp, start = 25.dp, end = 25.dp)
    ) {
        LoginHeader(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(20.dp))
        LoginForm(modifier = Modifier.align(Alignment.CenterHorizontally), navController)
        LoginBottom({
            navController.navigate(OtpRoute)
        })
    }
}