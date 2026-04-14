package com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.viewmodel.UploadedViewModel
import com.example.smsrly.presentation.ui.screens.showdetails.components.CustomDetailsButton

@Composable
fun UploadedPriceSection(price: Double,onClick:()->Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //can only delete
        Text("${price} EGP", fontSize = 17.sp, fontWeight = FontWeight.Medium)
        CustomDetailsButton({
                onClick()
        }, "Delete Realestate", Color.Red,)
    }
}