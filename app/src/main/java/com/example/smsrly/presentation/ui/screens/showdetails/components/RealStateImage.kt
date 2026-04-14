package com.example.smsrly.presentation.ui.screens.showdetails.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.sell.components.ImagesSwiper

@Composable
fun RealStateImage(images: List<String>) {
    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp)

        ){page->

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                model = images[page],
                error = painterResource(R.drawable.home),
                placeholder = painterResource(R.drawable.loading)
            )
        }
    }
}