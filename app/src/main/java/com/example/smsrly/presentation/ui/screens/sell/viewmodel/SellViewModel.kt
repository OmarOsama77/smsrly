package com.example.smsrly.presentation.ui.screens.sell.viewmodel


import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.usecase.realstateusecase.UploadRealStateUseCase
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.events.SellEvents
import com.example.smsrly.presentation.ui.screens.sell.viewmodel.states.SellState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    private val uploadRealStateUseCase: UploadRealStateUseCase,
) :
    ViewModel() {
    private val _selectedImages = MutableStateFlow<List<Uri>>(emptyList())
    val selectedImages: StateFlow<List<Uri>> = _selectedImages


    fun addImages(uris: List<Uri>) {
        _selectedImages.value = emptyList()
        _selectedImages.value = _selectedImages.value + uris
    }


    private val _selectedLocation = MutableStateFlow<Map<String, Any>?>(null)


    fun setLocation(long: Double, lat: Double, city: String, country: String) {
        _selectedLocation.value = mapOf(
            "longitude" to long,
            "latitude" to lat,
            "city" to city,
            "country" to country
        )

    }


    fun reset() {
        _selectedImages.value = emptyList()
        _selectedLocation.value = null
        _state.value= SellState.Idle
    }


    private val _events = MutableSharedFlow<SellEvents>()
    val events = _events
    private val _state = MutableStateFlow<SellState>(SellState.Idle)
    val state = _state
    fun uploadARealState(
        title: String,
        desc: String,
        price: String,
        floor: String,
        rooms: String,
        bathRoom: String,
        area: String,
    ) {
        viewModelScope.launch {
            try {
                _state.value = SellState.Loading
                val realState = RealEstate(
                    title,
                    desc,
                    area.toDouble(),
                    floor.toInt(),
                    bathRoom.toInt(),
                    price.toDouble(),
                    _selectedLocation.value?.get("city") as String,
                    _selectedLocation.value?.get("country") as String,
                    _selectedLocation.value?.get("latitude") as Double,
                     null,
                    _selectedLocation.value?.get("longitude") as Double,
                    rooms.toInt(),
                    _selectedImages.value.map {
                        it.toString()
                    },
                    null,
                            null,
                    null,
                    null
                )
                val res = uploadRealStateUseCase.uploadRealState(realState)
                if (res.isSuccess) {
                   _events.emit(SellEvents.ShowToast(res.getOrNull()!!))
                    _state.value = SellState.Success
                } else {
                    _events.emit(SellEvents.ShowToast(res.exceptionOrNull()?.message!!))
                    _state.value = SellState.Failed
                }


            } catch (e: Exception) {
                Log.d("catch the error  ", e.toString())
            }
        }
    }




}