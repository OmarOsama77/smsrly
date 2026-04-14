package com.example.smsrly.presentation.ui.screens.showdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.R

@Composable
fun RealStateDetails(title: String, roomNum: Int, floorNum: Int, bathRoomNumb: Int, area: Double) {
    Text(title, fontSize = 17.sp, fontWeight = FontWeight.Medium)
    Spacer(Modifier.height(22.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShowDetailsItem(painterResource(R.drawable.bed), roomNum.toString())
        Spacer(Modifier.width(17.dp))
        ShowDetailsItem(painterResource(R.drawable.stairss), floorNum.toString())
        Spacer(Modifier.width(17.dp))
        ShowDetailsItem(painterResource(R.drawable.bath), bathRoomNumb.toString())
        Spacer(Modifier.width(17.dp))
        ShowDetailsItem(painterResource(R.drawable.area), area.toString())
    }
}