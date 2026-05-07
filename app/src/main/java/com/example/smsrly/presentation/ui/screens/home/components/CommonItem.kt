package com.example.smsrly.presentation.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smsrly.R
import coil.compose.AsyncImage
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.presentation.ui.screens.home.viewmodel.HomeViewModel
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import com.example.smsrly.presentation.ui.theme.Primary

@Composable
fun CommonItem(navController: NavController, realEstate: RealEstate, viewModel: HomeViewModel) {

    Box(
        modifier = Modifier
            .height(233.dp)
            .width(254.dp)
            .clip(RoundedCornerShape(30.dp))
            .clickable {
                navController.navigate(ShowDetailsRoute(realEstate.id!!,false))
            }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            model = if (realEstate.images.isEmpty() || realEstate.images[0].isBlank())
                R.drawable.noimage
            else
                realEstate.images[0],
            error = painterResource(R.drawable.cross),
            placeholder = painterResource(R.drawable.loading),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xAA000000)
                        )
                    )
                )
        )
        IconButton(
            {
                if (realEstate.isSaved!!) {
                    //press to cancel
                    viewModel.unSaveARealEstate(realEstate.id!!)
                } else {
                    //press to save
                    viewModel.saveARealEstate(realEstate.id!!)
                }
            }, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp)

        ) {
            Icon(
                tint = Primary,
                painter = painterResource(if (realEstate.isSaved!!) R.drawable.addedtofav else R.drawable.save),
                contentDescription = null,
                modifier = Modifier.size(30.dp),

            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 12.dp, start = 12.dp)
        ) {
            Text(
                realEstate.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF)
            )
            Text(
                "${realEstate.country},${realEstate.city}", color = Color(0xFFBCBCBC)
            )
        }
    }
}