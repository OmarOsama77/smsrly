package com.example.smsrly.presentation.ui.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.usecase.realstateusecase.ResetRealEstatesUseCase
import com.example.smsrly.domain.usecase.tokenusecases.DeleteTokensUseCase
import com.example.smsrly.domain.usecase.userusecase.DeleteUserUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.example.smsrly.domain.usecase.userusecase.LogoutUseCase
import com.example.smsrly.presentation.ui.screens.settings.state.SettingsUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val deleteTokensUseCase: DeleteTokensUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val resetRealEstatesUseCase: ResetRealEstatesUseCase,
    private val deleteUserUseCase : DeleteUserUseCase

): ViewModel(){
    val user  = getUserFlowUseCase.getUser()
    private val _state = MutableStateFlow<SettingsUserState>(SettingsUserState.Idle)
    val state : StateFlow<SettingsUserState> = _state

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent : SharedFlow<String> = _errorEvent
    init {
        fetchUserData()
    }
    fun fetchUserData(){
        _state.value = SettingsUserState.Loading
        viewModelScope.launch {
            getUserDataUseCase.fetchUserData()
            if(user.value!=null){
                _state.value = SettingsUserState.Success
            }else{
                _state.value = SettingsUserState.Failed
            }
        }
    }
    fun logOut(){
        deleteTokensUseCase.resetToken()
        logoutUseCase.logout()
        resetRealEstatesUseCase.resetRealEstates()
        _state.value = SettingsUserState.LoggedOut

    }
    fun deleteAccount(){
        viewModelScope.launch {
            val res = deleteUserUseCase.deleteUser()
            if(res.isSuccess){
                _state.value = SettingsUserState.AccountDeleted(res.getOrNull()!!)
            }else{
                _errorEvent.emit(res.exceptionOrNull()!!.message!!)
            }
        }
    }
}