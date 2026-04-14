package com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.realstateusecase.UserUploadsUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import com.example.smsrly.presentation.ui.screens.myadds.screens.uploaded.state.UploadsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadedViewModel @Inject constructor(
    private val userUploadsUseCase: UserUploadsUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UploadsState>(UploadsState.Idle)
    val state : StateFlow<UploadsState> = _state





    fun getUserUploads(){
        _state.value = UploadsState.Loading
        viewModelScope.launch {
            val res = userUploadsUseCase.getUserUploads()
            if(res.isSuccess){
                _state.value = UploadsState.Success(res.getOrNull()!!)
            }else{
                _state.value = UploadsState.Failure(res.exceptionOrNull()!!.message!!)
            }
        }
    }

}