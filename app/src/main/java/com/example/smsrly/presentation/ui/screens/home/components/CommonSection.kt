package com.example.smsrly.presentation.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.components.EmptyData
import com.example.smsrly.presentation.ui.screens.home.viewmodel.HomeViewModel
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.NearestRealEstateState

@Composable
fun CommonSection(navController: NavController, viewModel: HomeViewModel) {
    val nearestRealEstateState = viewModel.nearestRealEstateState.collectAsState()
    val nearestRealEstates = viewModel.nearestRealEstates.collectAsState()
    LazyRow(
        modifier = Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        when (nearestRealEstateState.value) {
            is NearestRealEstateState.Idle -> {

            }

            is NearestRealEstateState.Loading -> {
                items(2) {
                    CommonItemShimmer()
                }
            }

            is NearestRealEstateState.Success -> {
                if (nearestRealEstates.value.isEmpty()) {
                    item {
                        EmptyData(
                            modifier = Modifier.fillParentMaxSize(),
                            message = "Nothing found"
                        )
                    }
                }
                items(nearestRealEstates.value.size) { index ->
                    CommonItem(
                        navController,
                        nearestRealEstates.value[index],
                        viewModel
                    )
                }
            }

            is NearestRealEstateState.Failed -> {
                item {
                    EmptyData(
                        modifier = Modifier.fillParentMaxSize(),
                        message = "Failed Fetching Data"
                    )
                }
            }
        }

    }
}
