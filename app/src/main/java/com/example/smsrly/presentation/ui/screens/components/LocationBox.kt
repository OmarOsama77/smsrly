package com.example.smsrly.presentation.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.locationPicker.MapPickerRoute
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel


@Composable
fun LocationBox(
    navController: NavController,
    viewModel: SellViewModel
) {
    val navBackStackEntry = navController.currentBackStackEntry


    val selectedLocation = remember {
        navBackStackEntry?.savedStateHandle?.get<Map<String, Any>>("selectedLocation")
    }

    selectedLocation?.let {
        viewModel.setLocation(
            selectedLocation["longitude"] as Double,
            selectedLocation["latitude"] as Double,
            selectedLocation["city"] as String,
            selectedLocation["country"] as String
        )
        navBackStackEntry?.savedStateHandle?.remove<Map<String, Any>>("selectedLocation")
    }




    Box(
        modifier = Modifier
            .height(151.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(MapPickerRoute)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.location),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
