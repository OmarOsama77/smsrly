package com.example.smsrly.presentation.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.realstateusecase.GetAllRealEstatesUseCase
import com.example.smsrly.domain.usecase.realstateusecase.GetNearestRealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SaveARealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.UnSaveARealEstateUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.AllRealEstatesState
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.NearestRealEstateState
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getAllRealEstateUseCase: GetAllRealEstatesUseCase,
    private val getNearestRealEstateUseCase: GetNearestRealEstateUseCase,
    private val saveARealEstateUseCase: SaveARealEstateUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase
) :
    ViewModel() {

    private val _allRealEstatesState = MutableStateFlow<AllRealEstatesState>(AllRealEstatesState.Idle)
    val allRealEstatesState: StateFlow<AllRealEstatesState> = _allRealEstatesState
    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    val user: StateFlow<User?> = getUserFlowUseCase.getUser()

    val allRealEstates: StateFlow<Map<Int, RealEstate>> =
        getAllRealEstateUseCase.getRealEstatesObj()
    private val _nearestRealEstateState =
        MutableStateFlow<NearestRealEstateState>(NearestRealEstateState.Idle)
    val nearestRealEstateState : StateFlow<NearestRealEstateState> = _nearestRealEstateState

    val nearestRealEstate = getNearestRealEstateUseCase.getNearestRealEstateObject()

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent : SharedFlow<String> = _errorEvent


    init {
        fetchUserData()
        fetchAllRealEstates()
        fetchNearestRealEstate()
    }


    fun fetchUserData() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            val res = getUserDataUseCase.fetchUserData()
            if (res.isSuccess) {
                _userState.value = UserState.Success
            } else {
                val message = res.exceptionOrNull()?.message?:"Unknown error"
                _errorEvent.emit(message)
                _userState.value = UserState.Failed(message)
            }

        }
    }

    fun fetchAllRealEstates() {
        _allRealEstatesState.value = AllRealEstatesState.Loading
        viewModelScope.launch {
            val res = getAllRealEstateUseCase.getAllRealEstates()
            if (res.isSuccess) {
                _allRealEstatesState.value = AllRealEstatesState.Success
            } else {
                val message = res.exceptionOrNull()?.message?:"Unknown error"
                _allRealEstatesState.value =
                    AllRealEstatesState.Failed(message)
                _errorEvent.emit(message)
            }
        }
    }

    fun fetchNearestRealEstate() {
        _nearestRealEstateState.value = NearestRealEstateState.Loading
        viewModelScope.launch {
            val res = getNearestRealEstateUseCase.getNearestRealEstate()
            if (res.isSuccess) {
                _nearestRealEstateState.value = NearestRealEstateState.Success
            } else {
                val message = res.exceptionOrNull()?.message?:"Unknown error"
                _nearestRealEstateState.value =
                    NearestRealEstateState.Failed(message)
                _errorEvent.emit(message)
            }
        }
    }




    fun saveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = saveARealEstateUseCase.saveARealEstate(id)
            if (res.isFailure) {
                val message  = res.exceptionOrNull()?.message?:"Unknown error"
                _errorEvent.emit(message)
            }
        }
    }

    fun unSaveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = unSaveARealEstateUseCase.unSaveARealEstate(id)
            if (res.isFailure) {
                val message  = res.exceptionOrNull()?.message?:"Unknown error"
                _errorEvent.emit(message)
            }
        }
    }
}



