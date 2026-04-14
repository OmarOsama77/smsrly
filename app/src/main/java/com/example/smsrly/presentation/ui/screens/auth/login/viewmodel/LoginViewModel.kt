package com.example.smsrly.presentation.ui.screens.auth.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.usecase.authusecases.LoginUseCase
import com.example.smsrly.domain.usecase.tokenusecases.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {


    private var _loginState = MutableStateFlow<LoginState>(LoginState.Ideal)
    var loginState: StateFlow<LoginState> = _loginState

    private var _loginEvents = MutableSharedFlow<LoginEvents>()
    var loginEvents: SharedFlow<LoginEvents> = _loginEvents

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val response = useCase.login(email, pass)


            if (response.isSuccess) {
                saveTokenUseCase.saveTokens(
                   response.getOrNull()!!.accessToken,
                    response.getOrNull()!!.refreshToken
                )

                _loginEvents.emit(LoginEvents.Success)
                _loginState.value = LoginState.Success
            } else {
                _loginEvents.emit(
                    LoginEvents.Failed(
                        response.exceptionOrNull()?.message ?: "Login failed"
                    )
                )
                _loginState.value = LoginState.Failed
            }


        }
    }


}