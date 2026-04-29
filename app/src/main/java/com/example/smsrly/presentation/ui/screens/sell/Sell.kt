package com.example.smsrly.presentation.ui.screens.sell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.domain.observer.IConnectivityObserver
import com.example.smsrly.presentation.ui.screens.nointernet.NoInternet
import com.example.smsrly.presentation.ui.screens.sell.components.AddsBody
import com.example.smsrly.presentation.ui.screens.sell.components.AddsHeader
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel
import kotlinx.serialization.Serializable

@Serializable
data object SellRoute

@Composable
fun Sell(navController: NavController) {
    val scrollState = rememberScrollState()
    val viewModel: SellViewModel = hiltViewModel()
    val networkStatus = viewModel.networkStatus.collectAsState()
    if (networkStatus.value == IConnectivityObserver.Status.UnAvailable) {
        NoInternet()
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
        ) {

            AddsHeader(Modifier.align(Alignment.CenterHorizontally), viewModel)
            Spacer(Modifier.height(20.dp))
            AddsBody(Modifier.align(Alignment.CenterHorizontally), navController, viewModel)


        }
    }
}