package com.example.smsrly.presentation.ui.screens.myadds.screens.saved.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.usecase.realstateusecase.GetUserSavedRealEstatesUseCase
import com.example.smsrly.domain.usecase.realstateusecase.UnSaveARealEstateUseCase
import com.example.smsrly.presentation.ui.screens.myadds.screens.saved.viewmodel.state.SavedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getUserSavedRealEstatesUseCase: GetUserSavedRealEstatesUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase
) : ViewModel() {

    private val _savedState = MutableStateFlow<SavedState>(SavedState.Idle)
    val savedState : StateFlow<SavedState> = _savedState

    private val _savedRealEstates = MutableStateFlow<List<RealEstate>>(emptyList())
    val savedRealEstates : StateFlow<List<RealEstate>> = _savedRealEstates
    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent
    fun getUserSaved() {
        _savedState.value = SavedState.Loading
        viewModelScope.launch {
            getUserSavedRealEstatesUseCase.getUserSavedRealEstates().collect {savedRealEstates->
                _savedRealEstates.value = savedRealEstates
                _savedState.value = SavedState.Success
            }
        }
    }

    init {
        getUserSaved()
    }


    fun unSave(id: Int) {
        viewModelScope.launch {
            val res = unSaveARealEstateUseCase.unSaveARealEstate(id)
            if (res.isFailure) {
                val message = res.exceptionOrNull()?.message ?: "Unknown error"
                _errorEvent.emit(message)
            }
        }
    }

}



