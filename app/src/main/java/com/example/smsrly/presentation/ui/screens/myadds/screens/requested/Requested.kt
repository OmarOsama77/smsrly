package com.example.smsrly.presentation.ui.screens.myadds.screens.requested

import androidx.compose.foundation.layout.Arrangement
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
import com.example.smsrly.presentation.ui.screens.myadds.components.MyAddsItem
import com.example.smsrly.presentation.ui.screens.myadds.components.MyAddsItemShimmer
import com.example.smsrly.presentation.ui.screens.myadds.screens.requested.viewmodel.state.RequestsState
import com.example.smsrly.presentation.ui.screens.myadds.screens.requested.viewmodel.RequestedViewModel

@Composable
fun Requested(navController: NavController) {
    val viewModel : RequestedViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val userRequests = viewModel.userRequests.collectAsState()


    LazyColumn(
        modifier=Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if(state.value is RequestsState.Loading){
            items(4){
                MyAddsItemShimmer()
            }
        }else if(state.value is RequestsState.Success){
            if(userRequests.value.isEmpty()){
                item{   EmptyData(
                    modifier = Modifier.fillParentMaxSize(),"No requested realestates"
                )}
            }else{
            items(userRequests.value.size){index->
                val realEstate = userRequests.value[index]
                MyAddsItem(navController,userRequests.value[index],{
                   if(userRequests.value[index].isSaved!!){

                       viewModel.unSaveARealEstate(realEstate.id!!)
                   }else{
                       viewModel.saveARealEstate(realEstate.id!!)
                   }
                })
            }
            }
        }else{
           item {    EmptyData(
               modifier = Modifier.fillParentMaxSize(),"Failed getting Requests"
           ) }
        }
    }
}