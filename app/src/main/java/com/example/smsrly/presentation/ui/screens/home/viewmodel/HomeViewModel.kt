package com.example.smsrly.presentation.ui.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.realstateusecase.GetAllRealEstatesUseCase
import com.example.smsrly.domain.usecase.realstateusecase.GetNearestRealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SaveARealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.UnSaveARealEstateUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.AllRealEstatesState
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.NearestRealEstateState
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getAllRealEstateUseCase: GetAllRealEstatesUseCase,
    private val getNearestRealEstateUseCase: GetNearestRealEstateUseCase,
    private val saveARealEstateUseCase: SaveARealEstateUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase,
) :
    ViewModel() {

    private val _allRealEstatesState =
        MutableStateFlow<AllRealEstatesState>(AllRealEstatesState.Idle)
    val allRealEstatesState: StateFlow<AllRealEstatesState> = _allRealEstatesState
    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _allRealEstates = MutableStateFlow<List<RealEstate>>(emptyList())
    val allRealEstates: StateFlow<List<RealEstate>> = _allRealEstates
    private val _nearestRealEstateState =
        MutableStateFlow<NearestRealEstateState>(NearestRealEstateState.Idle)
    val nearestRealEstateState: StateFlow<NearestRealEstateState> = _nearestRealEstateState

    private val _nearestRealEstates = MutableStateFlow<List<RealEstate>>(emptyList())
    val nearestRealEstates: StateFlow<List<RealEstate>> = _nearestRealEstates

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent


    init {
        getUserData()
        getAllRealEstates()
        getNearestRealEstates()
    }


    fun getUserData() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            getUserDataUseCase().collect { user ->
                _user.value = user
                _userState.value = UserState.Success
            }
        }
    }

    fun getAllRealEstates() {
        _allRealEstatesState.value = AllRealEstatesState.Loading
        viewModelScope.launch {
            getAllRealEstateUseCase.getAllRealEstates().collect { realEstatesList ->
                _allRealEstatesState.value = AllRealEstatesState.Success
                _allRealEstates.value = realEstatesList
            }

        }
    }

    fun getNearestRealEstates() {
        _nearestRealEstateState.value = NearestRealEstateState.Loading
        viewModelScope.launch {
            getNearestRealEstateUseCase().collect { realEstatesList ->
                _nearestRealEstateState.value = NearestRealEstateState.Success
                _nearestRealEstates.value = realEstatesList
            }
        }
    }


    fun saveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = saveARealEstateUseCase.saveARealEstate(id)

            if (res.isFailure) {
                _errorEvent.emit(res.exceptionOrNull()?.message ?: "Unknown error")
            } else {
                Log.d("om here", "i thin")
                Log.d("the res ", res.getOrNull()!!)
            }
        }
    }

    fun unSaveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = unSaveARealEstateUseCase.unSaveARealEstate(id)
            if (res.isFailure) {
                val message = res.exceptionOrNull()?.message ?: "Unknown error"
                _errorEvent.emit(message)
            }
        }
    }


}



