package com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.smsrly.presentation.ui.screens.myadds.components.MyAddsItemShimmer
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.components.UploadedItem
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.state.UploadsState
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.viewmodel.UploadedViewModel

@Composable
fun Uploaded(navController: NavController) {

    val viewModel: UploadedViewModel = hiltViewModel()
    val uploadsState = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getUserUploads()
    }

    LazyColumn(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (uploadsState.value) {
            is UploadsState.Idle -> {

            }
            is UploadsState.Loading -> {
                items(4) {
                    MyAddsItemShimmer()
                }
            }

            is UploadsState.Success -> {
                val data = (uploadsState.value as UploadsState.Success).data
                if (data.isEmpty()) {
                    item { EmptyData(modifier = Modifier.fillParentMaxSize(), "No uploads found") }
                } else {
                    items(data.size) { index ->
                        UploadedItem(navController, data[index])
                    }
                }
            }

            is UploadsState.Failure -> {
                val message = (uploadsState.value as UploadsState.Failure).message
                item { EmptyData(modifier = Modifier.fillParentMaxSize(), message) }
            }
        }

    }

}


