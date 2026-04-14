package com.example.smsrly.presentation.ui.screens.auth.splash.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.usecase.tokenusecases.CheckAuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAuthenticationUseCase: CheckAuthenticationUseCase
) : ViewModel() {

    private val _event = MutableSharedFlow<SplashEvents>(1)
    val event: SharedFlow<SplashEvents> = _event


    fun hasValidRefreshToken() {
        viewModelScope.launch {
            if (checkAuthenticationUseCase.hasValidRefreshToken()) {
                _event.emit(SplashEvents.Success)
            } else {
                _event.emit(SplashEvents.Failed)
            }
        }
    }

    init {
        hasValidRefreshToken()
    }
}