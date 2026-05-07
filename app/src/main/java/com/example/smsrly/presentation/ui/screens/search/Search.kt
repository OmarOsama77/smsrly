package com.example.smsrly.presentation.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smsrly.presentation.ui.screens.components.EmptyData
import com.example.smsrly.presentation.ui.screens.components.ItemShimmer
import com.example.smsrly.presentation.ui.screens.home.components.Item
import com.example.smsrly.presentation.ui.screens.search.components.SearchBody
import com.example.smsrly.presentation.ui.screens.search.components.SearchHeader
import com.example.smsrly.presentation.ui.screens.search.viewmodel.SearchViewModel
import com.example.smsrly.presentation.ui.screens.search.viewmodel.states.SearchState
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetails
import com.example.smsrly.presentation.ui.screens.showdetails.ShowDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

@Composable
fun Search(navController: NavController) {
    val viewModel : SearchViewModel = hiltViewModel()
    val title  = remember{ mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val data = viewModel.data.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetData()
        }
    }


    LazyColumn(
        modifier = Modifier
            .padding(top = 50.dp, start = 15.dp, end = 10.dp)
            .fillMaxWidth()
    ) {
        item {
            SearchHeader(title.value, {
                title.value = it
                viewModel.onQueryChanged(it)
            })
        }
        item { Spacer(Modifier.height(10.dp)) }
        item { SearchBody() }
        when(state.value){
            is SearchState.Idle -> {
                item { EmptyData(message = "Start Searching to see Results") }
            }
            is SearchState.Loading -> {
                items(12) { ItemShimmer() }
            }
            is SearchState.Success -> {
                if(data.value.isEmpty()){
                    item { EmptyData(message = "Nothing Found") }
                }else{
                    items(data.value.size){index->
                        val realEstate = data.value[index]
                        Item(data.value[index],{
                            if (realEstate.isSaved!!) {
                                //press to cancel
                                viewModel.unSaveARealEstate(realEstate.id!!)
                            } else {
                                //press to save
                                viewModel.saveARealEstate(realEstate.id!!)
                            }
                        },{
                            navController.navigate(ShowDetailsRoute(realEstate.id!!,false))
                        })
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
            is SearchState.Failed -> {
                val failedMessage = (state.value as SearchState.Failed).message
                item { EmptyData(message = failedMessage) }

            }
        }
    }

}


