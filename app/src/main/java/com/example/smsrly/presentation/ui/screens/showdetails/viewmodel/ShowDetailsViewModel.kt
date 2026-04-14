package com.example.smsrly.presentation.ui.screens.showdetails.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.usecase.realstateusecase.CancelRequestUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SendRequestUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.example.smsrly.presentation.ui.screens.showdetails.viewmodel.state.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private val sendRequestUseCase: SendRequestUseCase,
    private val cancelRequestUseCase: CancelRequestUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _isRequested = MutableStateFlow<Boolean>(false)
    val isRequested = _isRequested
    init {
        loadUserData()
    }
    val currentUser  = getUserFlowUseCase.getUser()
    fun loadUserData(){
        viewModelScope.launch {
            getUserDataUseCase.fetchUserData()
        }
    }
    fun initializeRealEstate(isRequested:Boolean) {
        _isRequested.value = isRequested
    }

    private val _state = MutableStateFlow<RequestState>(RequestState.Idle)
    val state: StateFlow<RequestState> = _state
    fun sendRequest(id: Int) {
        toggleRequested()
        _state.value = RequestState.Loading
        viewModelScope.launch {
            val res = sendRequestUseCase.sendRequest(id)
            if (res.isSuccess) {
                _state.value = RequestState.Success(res.getOrNull()!!)
            } else {
                toggleRequested()
                _state.value = RequestState.Failed(res.exceptionOrNull()!!.message!!)
            }
        }
    }

    fun toggleRequested() {
         _isRequested.value = !isRequested.value!!
    }
    fun cancelRequest(id:Int){
        toggleRequested()
        _state.value = RequestState.Loading
        viewModelScope.launch {
            val res = cancelRequestUseCase.cancelRequest(id)
            if(res.isSuccess){
             _state.value = RequestState.Success(res.getOrNull()!!)
            }else{
                toggleRequested()
                _state.value = RequestState.Failed(res.exceptionOrNull()!!.message!!)
            }
        }
    }
}