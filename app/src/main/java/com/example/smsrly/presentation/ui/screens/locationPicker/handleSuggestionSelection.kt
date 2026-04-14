package com.example.smsrly.presentation.ui.screens.locationPicker

import android.util.Log
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.search.ResponseInfo
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchSelectionCallback
import com.mapbox.search.result.SearchSuggestion
import com.mapbox.search.result.SearchResult  // Correct import


// Function to handle Suggestion click
fun handleSuggestionSelection(
    suggestion: SearchSuggestion,
    searchEngine: SearchEngine,
    mapViewportState: MapViewportState,
    onSuggestionSelected: (SearchResult) -> Unit

) {
    searchEngine.select(suggestion, object: SearchSelectionCallback {
        override fun onResult(
            suggestion: SearchSuggestion,
            result: SearchResult,
            responseInfo: ResponseInfo
        ) {
            // When user selects a suggestion:
            onSuggestionSelected(result)
            val coordinate = result.coordinate
            // Set the center of the cameraOptions to the result coordinate
            val camera = cameraOptions {
                center(coordinate)
                zoom(14.0)
            }

            val animationOptions = MapAnimationOptions.Builder()
                .duration(3000L) // 3 seconds
                .build()

            // Fly the map to the result location
            mapViewportState.flyTo(camera, animationOptions)
        }

        override fun onResults(
            suggestion: SearchSuggestion,
            results: List<SearchResult>,
            responseInfo: ResponseInfo
        ) {
            // handle multiple results (category, brand, etc.)
        }


        override fun onSuggestions(
            suggestions: List<SearchSuggestion>,
            responseInfo: ResponseInfo
        ) {
            // override if needed
        }

        override fun onError(e: Exception) {
            Log.e("Search", "Selection error", e)
        }
    })
}