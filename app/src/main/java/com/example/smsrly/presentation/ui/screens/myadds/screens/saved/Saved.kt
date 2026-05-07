package com.example.smsrly.presentation.ui.screens.myadds.screens.saved

import android.widget.Toast
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
import com.example.smsrly.presentation.ui.screens.myadds.screens.saved.viewmodel.SavedViewModel
import com.example.smsrly.presentation.ui.screens.myadds.screens.saved.viewmodel.state.SavedState

@Composable
fun Saved(navController: NavController) {
    val viewModel: SavedViewModel = hiltViewModel()
    val state = viewModel.savedState.collectAsState()
    val savedRealEstate = viewModel.savedRealEstates.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.errorEvent.collect {error->
            Toast.makeText(navController.context, error, Toast.LENGTH_SHORT).show()
        }
    }
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (state.value is SavedState.Loading) {

            items(4) { MyAddsItemShimmer() }
        } else if (state.value is SavedState.Success) {
            if (savedRealEstate.value.isEmpty()) {
                item {
                    EmptyData(
                        modifier = Modifier.fillParentMaxSize(),
                        "No saved realestates"
                    )

                }
            } else {
                items(savedRealEstate.value.size) { index ->
                    MyAddsItem(navController, savedRealEstate.value[index], {
                        viewModel.unSave(savedRealEstate.value[index].id!!)
                    })
                }
            }
        } else {
            item { EmptyData( modifier = Modifier.fillParentMaxSize(),"Failed fetching data") }
        }
    }
}