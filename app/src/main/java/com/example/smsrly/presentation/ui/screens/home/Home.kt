package com.example.smsrly.presentation.ui.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.components.EmptyData
import com.example.smsrly.presentation.ui.screens.components.ItemShimmer
import com.example.smsrly.presentation.ui.screens.home.components.CommonSection
import com.example.smsrly.presentation.ui.screens.home.components.HomeHeader
import com.example.smsrly.presentation.ui.screens.home.components.Item
import com.example.smsrly.presentation.ui.screens.home.components.MoreSection
import com.example.smsrly.presentation.ui.screens.home.viewmodel.HomeViewModel
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.AllRealEstatesState
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Composable

fun Home(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val allRealEstateState = viewModel.allRealEstatesState.collectAsState()
    val allRealEstate = viewModel.allRealEstates.collectAsState()
    val user = viewModel.user.collectAsState()
    val userState = viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.errorEvent.collect { event ->
            Toast.makeText(navController.context, event, Toast.LENGTH_SHORT).show()
        }
    }


    LazyColumn(
        modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp)
    ) {

        item { HomeHeader(user.value,userState.value,navController) }

        item { Spacer(Modifier.height(20.dp)) }


        item { CommonSection(navController, viewModel) }

        item { Spacer(Modifier.height(20.dp)) }
        item { MoreSection() }

        when (allRealEstateState.value) {
            is AllRealEstatesState.Idle -> {

            }

            is AllRealEstatesState.Loading -> {
                items(6) {
                    ItemShimmer()
                    Spacer(Modifier.height(10.dp))
                }
            }

            is AllRealEstatesState.Success -> {
                if (allRealEstate.value.isEmpty()) {
                    item { EmptyData(modifier = Modifier.height(200.dp), "No data found") }
                } else {
                    items(allRealEstate.value.size) { index ->
                        Item(
                            allRealEstate.value[index],
                            {
                                if (allRealEstate.value[index].isSaved!!) {
                                    //press to cancel
                                    viewModel.unSaveARealEstate(allRealEstate.value[index].id!!)
                                } else {
                                    //press to save
                                    viewModel.saveARealEstate(allRealEstate.value[index].id!!)
                                }
                            },
                            {
                                navController.navigate(ShowDetailsRoute(allRealEstate.value[index].id!!, false))
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }

            is AllRealEstatesState.Failed -> {
                item { EmptyData(modifier = Modifier.height(200.dp), "Failed fetching data") }
            }
        }


    }

}