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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val savedRealEstatesUseCase: GetUserSavedRealEstatesUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase
) : ViewModel() {

    private val _savedState = MutableStateFlow<SavedState>(SavedState.Idle)
    val savedState = _savedState


    lateinit var savedEstates: StateFlow<List<RealEstate>>
    fun getUserSaved() {
        _savedState.value = SavedState.Loading
        viewModelScope.launch {
            savedEstates =
                savedRealEstatesUseCase.getUserSavedRealEstates()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5000),
                        initialValue = emptyList()
                    )
            _savedState.value = SavedState.Success
        }
    }

    init {
        getUserSaved()
    }

    fun unSave(id: Int) {
        viewModelScope.launch {
            unSaveARealEstateUseCase.unSaveARealEstate(id)
        }
    }


}



