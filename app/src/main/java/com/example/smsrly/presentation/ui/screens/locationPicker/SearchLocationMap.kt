package com.example.smsrly.presentation.ui.screens.locationPicker

import com.mapbox.search.result.SearchResult
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsrly.R
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.search.ApiType
import com.mapbox.search.ResponseInfo
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchEngineSettings
import com.mapbox.search.SearchOptions
import com.mapbox.search.SearchSuggestionsCallback
import com.mapbox.search.result.SearchSuggestion
import java.util.Locale

@Composable
fun SearchLocationMap(
    modifier: Modifier = Modifier,
    mapViewportState: MapViewportState,
    onSuggestionSelected: (SearchResult) -> Unit,
    onSearchingChanged: (Boolean) -> Unit
) {
    var query by remember { mutableStateOf<String>("") }
    var suggestions by remember { mutableStateOf<List<SearchSuggestion>>(emptyList()) }

    val searchEngine = remember {
        SearchEngine.createSearchEngine(
            ApiType.SEARCH_BOX,
            SearchEngineSettings()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newQuery ->
                query = newQuery
                val isSearching = newQuery.isNotEmpty()
                onSearchingChanged(isSearching)
                if (newQuery.length >= 2) {
                    searchEngine.search(
                        newQuery,
                        SearchOptions(
                            limit = 10
                        ),
                        callback = object : SearchSuggestionsCallback {
                            override fun onSuggestions(
                                list: List<SearchSuggestion>,
                                responseInfo: ResponseInfo
                            ) {
                                suggestions = list
                            }

                            override fun onError(e: Exception) {
                                Log.e("SearchScreen", "Search error", e)
                            }
                        }
                    )
                } else {
                    suggestions = emptyList()
                }


            },
            label = { Text("Search", fontWeight = FontWeight.Bold, fontSize = 22.sp) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        // Suggestions list anchored right below the TextField
        if (suggestions.isNotEmpty()) {
            Surface(
                color = Color.White,
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .heightIn(max = 500.dp)
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    // Filter out category suggestion types
                    val filteredSuggestions = suggestions.filter { it.type !is Locale.Category }

                    filteredSuggestions.forEachIndexed { index, suggestion ->

                        // Get distance in kilometers if possible
                        val distanceKm = suggestion.distanceMeters?.div(1000.0)
                        val addressText = suggestion.fullAddress
                            ?: listOfNotNull(
                                suggestion.address?.region,
                                suggestion.address?.country
                            ).joinToString(", ")



                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                // Add click handler to suggestion element
                                .clickable {
                                    suggestions = emptyList()
                                    onSearchingChanged(false)
                                    handleSuggestionSelection(suggestion, searchEngine, mapViewportState, onSuggestionSelected)
                                }
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                        Icon(
                                painter = painterResource(id = R.drawable.pin),
                                contentDescription = "Mapbox Marker",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(24.dp)
                            )

                            Column(
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text(
                                    text = suggestion.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = addressText,
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 4.dp)
                                )

                                distanceKm?.let { km ->
                                    Text(
                                        text = String.format("%.1f km", km),
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                            }
                        }
                        if (index < suggestions.lastIndex) {
                            HorizontalDivider(color = Color.LightGray)
                        }
                    }

                }
            }
        }


    }
}
