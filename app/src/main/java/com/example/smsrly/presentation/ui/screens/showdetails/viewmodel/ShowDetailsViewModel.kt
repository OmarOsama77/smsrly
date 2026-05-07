package com.example.smsrly.presentation.ui.screens.showdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.realstateusecase.CancelRequestUseCase
import com.example.smsrly.domain.usecase.realstateusecase.GetRealEstateByIdUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SendRequestUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state.RequestState
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state.ShowDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val sendRequestUseCase: SendRequestUseCase,
    private val cancelRequestUseCase: CancelRequestUseCase,
    private val getRealEstateByIdUseCase: GetRealEstateByIdUseCase
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user


    private val _state = MutableStateFlow<RequestState>(RequestState.Idle)
    val state: StateFlow<RequestState> = _state

    init {
        getUserData()
    }
    fun getUserData() {
        viewModelScope.launch {
            getUserDataUseCase().collect { user ->
                _user.value = user

            }
        }
    }

    fun sendRequest(id: Int) {
        viewModelScope.launch {
            sendRequestUseCase.sendRequest(id)
        }
    }

    fun cancelRequest(id: Int) {
        viewModelScope.launch {
            cancelRequestUseCase.cancelRequest(id)
        }
    }

    private val _currentRealEstate = MutableStateFlow<RealEstate?>(null)
    val currentRealEstate: StateFlow<RealEstate?> = _currentRealEstate


    private val _currentRealEstateState = MutableStateFlow<ShowDetailsState>(ShowDetailsState.Idle)
    val currentRealEstateState : StateFlow<ShowDetailsState> = _currentRealEstateState
    fun getRealEstateById(id: Int) {
        _currentRealEstateState.value = ShowDetailsState.Loading
        viewModelScope.launch {
            getRealEstateByIdUseCase.getRealEstateById(id).collect { realEstate ->
                _currentRealEstate.value = realEstate
                _currentRealEstateState.value = ShowDetailsState.Success
            }
        }
    }
}