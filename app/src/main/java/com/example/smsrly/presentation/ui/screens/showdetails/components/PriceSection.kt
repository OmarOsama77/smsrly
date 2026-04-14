package com.example.smsrly.presentation.ui.screens.showdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.ShowDetailsViewModel
import com.example.smsrly.presentation.ui.theme.Primary
import com.example.smsrly.utility.formatPrice

@Composable
fun PriceSection(id: Int, price: Double, viewModel: ShowDetailsViewModel) {
   val isRequested = viewModel.isRequested.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("${formatPrice(price)} EGP", fontSize = 17.sp, fontWeight = FontWeight.Medium)
        CustomDetailsButton(
            {
                if(isRequested.value){
                    viewModel.cancelRequest(id)
                }else{
                    viewModel.sendRequest(id)
                }
            },
            if (isRequested.value) "Delete request" else "Request",
            if (isRequested.value) Color.Red else Primary
        )
    }
}