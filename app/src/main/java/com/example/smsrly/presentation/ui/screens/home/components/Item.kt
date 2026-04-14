package com.example.smsrly.presentation.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.smsrly.R
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.presentation.ui.screens.home.viewmodel.HomeViewModel
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import com.example.smsrly.presentation.ui.theme.Primary
import com.example.smsrly.utility.formatPrice

@Composable
fun Item(realEstate: RealEstate, onSavedClick: () -> Unit,onClicked:()->Unit) {

    Box(
        modifier = Modifier
            .height(109.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable {
                onClicked()
            }
            .background(color = Color(0xFFFFFFFF))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.width(120.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    model = if (realEstate.images.isEmpty() || realEstate.images[0].isBlank())
                        R.drawable.noimage
                    else
                        realEstate.images[0],
                    error = painterResource(R.drawable.home),
                    placeholder = painterResource(R.drawable.loading),
                )
                Column(
                    modifier = Modifier
                        .padding(top = 11.dp)
                        .width(170.dp)
                        .height(90.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(realEstate.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(
                        "${realEstate.country},${realEstate.city}",
                        color = Color(0xFF737373),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        "${realEstate.area}m^2",
                        color = Color(0xFF737373),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        "${formatPrice(realEstate.price)} EGP",
                        color = Primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            IconButton(onSavedClick) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(if (realEstate.isSaved!!) R.drawable.addedtofav else R.drawable.save),
                    contentDescription = null,
                    tint = Primary
                )
            }
        }
    }
}