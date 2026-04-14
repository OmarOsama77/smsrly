package com.example.smsrly.presentation.ui.screens.locationPicker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.smsrly.R
import com.example.smsrly.presentation.ui.screens.components.CustomButton
import com.example.smsrly.presentation.ui.theme.Primary
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.search.ApiType
import com.mapbox.search.ResponseInfo
import com.mapbox.search.ReverseGeoOptions
import com.mapbox.search.SearchCallback
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchEngineSettings
import com.mapbox.search.result.SearchResult
import kotlinx.serialization.Serializable

@Serializable
data object MapPickerRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPicker(
    navController: NavController,

    ) {
    val isSearching = remember { mutableStateOf(false) }

    val selectedResult = remember { mutableStateOf<SearchResult?>(null) }
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(31.2357, 30.0444))
            zoom(10.0)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        MapboxMap(
            mapViewportState = mapViewportState,
            modifier = Modifier.fillMaxSize(),
            scaleBar = {},
            compass = {},

            )
        SearchLocationMap(
            mapViewportState = mapViewportState,
            onSuggestionSelected = { result ->
                selectedResult.value = result
            },
            onSearchingChanged = { searching ->
                isSearching.value = searching
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1f)

        )




        Icon(
            painter = painterResource(id = R.drawable.pin),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(29.dp)
        )


        CustomButton(
            "Select", {
                val center = mapViewportState.cameraState!!.center
                val longitude = center.longitude()
                val latitude = center.latitude()
                var country: String? = null
                var city: String? = null
                val searchEngine = SearchEngine.createSearchEngine(
                    ApiType.SEARCH_BOX,
                    SearchEngineSettings()
                )

                val options = ReverseGeoOptions(
                    center = Point.fromLngLat(longitude, latitude),
                    limit = 1
                )

                searchEngine.search(options, object : SearchCallback {
                    override fun onResults(
                        results: List<SearchResult>,
                        responseInfo: ResponseInfo
                    ) {
                        val result = results.firstOrNull()

                        city = result?.address?.place
                        country = result?.address?.country

                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "selectedLocation",
                            mapOf(
                                "longitude" to longitude,
                                "latitude" to latitude,
                                "country" to country,
                                "city" to city
                            )

                        )
                        navController.popBackStack()
                    }

                    override fun onError(e: Exception) {

                    }
                })

//
//                Log.d("the data before sending ", "${city} ${country}")



            }, Primary, true, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )


    }
}