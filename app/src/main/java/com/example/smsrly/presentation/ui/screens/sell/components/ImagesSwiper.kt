package com.example.smsrly.presentation.ui.screens.sell.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.SellViewModel

@Composable
fun ImagesSwiper(viewModel : SellViewModel,onClick:()->Unit) {
    val images by viewModel.selectedImages.collectAsState()

    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Box(
            modifier = Modifier.clickable{
                onClick()
            }
        ){
            AsyncImage(
                model = images[page],
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)    ,
                contentScale = ContentScale.Crop
            )
        }
    }
}