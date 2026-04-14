package com.example.smsrly.presentation.ui.screens.auth.otp.viewmodel


import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.usecase.authusecases.OtpUseCase
import com.example.smsrly.utility.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpUseCase: OtpUseCase,
    private val locationHelper: LocationHelper
) : ViewModel() {
    private val _otpState = MutableStateFlow<OtpState>(OtpState.Idle)
    val otpState: StateFlow<OtpState> = _otpState


    private val _selectedImage = MutableStateFlow<Uri?>(null)
    val selectedImage: StateFlow<Uri?> = _selectedImage

    private val _events = MutableSharedFlow<OtpEvent>()
    val event: SharedFlow<OtpEvent> = _events
    fun sendOtp(
        firstName: String,
        lastName: String,
        email: String,

    ) {
        try {
            viewModelScope.launch {
                _otpState.value = OtpState.Loading
                val response = otpUseCase.sendOtp(
                    firstName,
                    lastName,
                    email,
                )

                if (response.isSuccess) {
                    _events.emit(OtpEvent.Success(response.getOrNull()!!))
                    _otpState.value = OtpState.Success
                } else {
                    _events.emit(
                        OtpEvent.Failed(
                            response.exceptionOrNull()!!.message!!
                        )
                    )
                    _otpState.value =
                        OtpState.Failed
                }

            }
        } catch (e: Exception) {
            Log.d("catch the error ", e.toString())
        }

    }


    fun setImage(uri: Uri?) {
        _selectedImage.value = uri
    }

      var location: Pair<Double, Double>? = null


    fun fetchLocation() {
        viewModelScope.launch {
            location = locationHelper.getLocation()
        }
    }
    fun getUserLat():Double{
        return location!!.first
    }
    fun getUserLong(): Double{
        return location!!.second
    }


}