package com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.observer.IConnectivityObserver
import com.example.smsrly.domain.usecase.networkobserverusecases.NetworkObserverUseCase
import com.example.smsrly.domain.usecase.tokenusecases.CheckAuthenticationUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFromServerUseCase
import com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel.states.SplashEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAuthenticationUseCase: CheckAuthenticationUseCase,
    private val getUserFromServerUseCase: GetUserFromServerUseCase
) : ViewModel() {

    private val _event = MutableSharedFlow<SplashEvents>(1)
    val event: SharedFlow<SplashEvents> = _event


    fun hasValidUser() {
        viewModelScope.launch {
            if (checkAuthenticationUseCase.hasValidRefreshToken()) {
                getUserFromServer()
            } else {
                _event.emit(SplashEvents.Failed)
            }
        }
    }


    init {
        hasValidUser()
    }


    fun getUserFromServer() {
        viewModelScope.launch {
            val res = getUserFromServerUseCase()
            if (res.isSuccess) {
                _event.emit(SplashEvents.Success)
            } else {
                _event.emit(SplashEvents.Failed)
            }
        }
    }
}