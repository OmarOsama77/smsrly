package com.example.smsrly.presentation.ui.screens.myadds.screens.requested.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.usecase.realstateusecase.GetUserRequestsUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SaveARealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.UnSaveARealEstateUseCase
import com.example.smsrly.presentation.ui.screens.myadds.screens.requested.viewmodel.state.RequestsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestedViewModel @Inject constructor(
    private val getUserRequestsUseCase: GetUserRequestsUseCase,
    private val saveARealEstateUseCase: SaveARealEstateUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<RequestsState>(RequestsState.Idle)
    val state: StateFlow<RequestsState> = _state

    lateinit var requested: StateFlow<List<RealEstate>>
    fun getUserRequests() {
        _state.value = RequestsState.Loading
        viewModelScope.launch {
            requested = getUserRequestsUseCase.getUserRequests()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    emptyList()
                )
            _state.value = RequestsState.Success
        }
    }
    init {
        getUserRequests()
    }

    fun saveARealEstate(id:Int) {
        viewModelScope.launch {
             saveARealEstateUseCase.saveARealEstate(id)
        }
    }

    fun unSaveARealEstate(id: Int) {
        viewModelScope.launch {
             unSaveARealEstateUseCase.unSaveARealEstate(id)

        }
    }


}