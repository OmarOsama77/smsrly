package com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.authusecases.SignupUseCase
import com.example.smsrly.domain.usecase.authusecases.UploadUserImageUseCase
import com.example.smsrly.utility.ImageUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val uploadUserImageUseCase: UploadUserImageUseCase,
    private val imageUtils: ImageUtils
) : ViewModel() {

    private val _signupState = MutableStateFlow<SignupState>(SignupState.Idle)
    val signupState: StateFlow<SignupState> = _signupState

    private val _events = MutableSharedFlow<SignupEvents>()
    val events: SharedFlow<SignupEvents> = _events

    fun signup(
        firstName:String,
        lastName:String,
        email:String,
        phoneNumber:String,
        latitude : Double,
        longitude:Double,
        password: String,
        confirmPass: String,
        otpCode: String,
        userImage:String?
    ) {
        val user = User (firstName,lastName,email,latitude,longitude,phoneNumber,userImage)
        viewModelScope.launch {
            _signupState.value = SignupState.Loading
            val response = signupUseCase.signup(user, password, confirmPass, otpCode)

            if (response.isSuccess) {

                if (user.imageUrl != null) {
                    val uri = user.imageUrl!!.toUri()
                    val imageFile = imageUtils.convertImageUriToJpgFile(uri)
                    val imageResponse = uploadUserImageUseCase.uploadUserImage(
                        response.getOrNull()!!.accessToken,
                        imageFile!!
                    )

                    if (imageResponse.isFailure) {
                        _events.emit(
                            SignupEvents.ImageUploadFailed(imageResponse.exceptionOrNull()!!.message!!)
                        )
                    }
                }

                _events.emit(SignupEvents.SignupSuccess("Account created Successfully"))
                _signupState.value = SignupState.Success
            } else {
                _events.emit(
                    SignupEvents.SignupFailed(
                        response.exceptionOrNull()!!.message!!
                    )
                )
                _signupState.value = SignupState.Failed
            }

        }
    }

}