package com.example.smsrly.presentation.ui.screens.showdetails

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.presentation.ui.screens.myadds.components.RequestedUsers
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.components.UploadedPriceSection
import com.example.smsrly.presentation.ui.screens.showdetails.components.LocationDisplay
import com.example.smsrly.presentation.ui.screens.showdetails.components.PriceSection
import com.example.smsrly.presentation.ui.screens.showdetails.components.RealStateDetails
import com.example.smsrly.presentation.ui.screens.showdetails.components.RealStateImage
import com.example.smsrly.presentation.ui.screens.showdetails.components.ShowDetailsCard
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.ShowDetailsViewModel
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state.RequestState
import com.example.smsrly.utility.RealEstateNavType
import com.example.smsrly.utility.UserInfoNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class ShowDetailsRoute(
    val realEstate: RealEstate,
    val showRequestedUsers: Boolean,

)

fun NavGraphBuilder.ShowDetailsRoute(navController: NavController) {
    composable<ShowDetailsRoute>(
        typeMap = mapOf(
            typeOf<RealEstate>() to RealEstateNavType,
            typeOf<UserInfo>() to UserInfoNavType
        )
    ) { backStackEntry ->
        val route = backStackEntry.toRoute<ShowDetailsRoute>()
        ShowDetails(navController, route.realEstate, route.showRequestedUsers)
    }
}

@Composable
fun ShowDetails(
    navController: NavController,
    realEstate: RealEstate,
    showRequestedUsers: Boolean,
) {


    val viewModel: ShowDetailsViewModel = hiltViewModel()
    val requestState = viewModel.state.collectAsState()
    val currentUser = viewModel.currentUser.collectAsState()

    LaunchedEffect(requestState.value) {
        if (requestState.value is RequestState.Success) {
            Toast.makeText(
                navController.context,
                (requestState.value as RequestState.Success).message,
                Toast.LENGTH_SHORT
            ).show()
        } else if (requestState.value is RequestState.Failed) {
            Toast.makeText(
                navController.context,
                (requestState.value as RequestState.Failed).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    if (!showRequestedUsers) {
        LaunchedEffect(Unit) {
            viewModel.initializeRealEstate(realEstate.isRequested!!)
        }
    }


    LazyColumn {


        item { RealStateImage(realEstate.images) }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 22.dp, end = 22.dp, bottom = 10.dp)
            ) {
                RealStateDetails(
                    realEstate.title,
                    realEstate.rooms,
                    realEstate.floor,
                    realEstate.bathRoom,
                    realEstate.area
                )

                Spacer(Modifier.height(30.dp))
                ShowDetailsCard(
                    navController,
                    if (showRequestedUsers) UserInfo(
                        currentUser.value!!.userId!!,
                        currentUser.value!!.phoneNumber,
                        currentUser.value!!.imageUrl,
                            currentUser.value!!.firstName + currentUser.value!!.lastName
                    ) else realEstate.uploaderInfo!!,
                    realEstate.desc
                )

                Spacer(Modifier.height(14.dp))
                LocationDisplay(realEstate.longitude, realEstate.latitude)
                Spacer(Modifier.height(14.dp))
                if (showRequestedUsers) {
                    UploadedPriceSection(realEstate.price, {

                    })
                } else {
                    PriceSection(realEstate.id!!, realEstate.price, viewModel)
                }
            }
        }
        if (showRequestedUsers) {
            item { Spacer(modifier = Modifier.height(12.dp)) }
            items(realEstate.requestedUsers!!.size) { index ->
                RequestedUsers(
                    realEstate.requestedUsers[index].userImage,
                    realEstate.requestedUsers[index].userName
                )
            }
        }


    }
}