package com.example.smsrly.presentation.ui.screens.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.usecase.realstateusecase.SaveARealEstateUseCase
import com.example.smsrly.domain.usecase.realstateusecase.SearchByTitleUseCase
import com.example.smsrly.domain.usecase.realstateusecase.UnSaveARealEstateUseCase
import com.example.smsrly.presentation.ui.screens.search.viewmodel.states.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchByTitleUseCase: SearchByTitleUseCase,
    private val saveARealEstateUseCase: SaveARealEstateUseCase,
    private val unSaveARealEstateUseCase: UnSaveARealEstateUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<SearchState>(SearchState.Idle)
    val state : StateFlow<SearchState> = _state


    private val _data = MutableStateFlow<List<RealEstate>>(emptyList())
    val data : StateFlow<List<RealEstate>> = _data
    private var searchJob : Job? = null

    fun onQueryChanged (query:String){
        if(query.isBlank()){
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            _state.value = SearchState.Loading
            val result = searchByTitleUseCase.searchByTitle(query)

            if(result.isSuccess){
                _state.value = SearchState.Success
                _data.value = result.getOrNull()!!

            }else{
                _state.value = SearchState.Failed(result.exceptionOrNull()?.message!!)
            }
        }
    }
    fun saveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = saveARealEstateUseCase.saveARealEstate(id)
            _data.value = _data.value.map {
                if(it.id==id){
                    it.copy(isSaved = true)
                }else{
                    it
                }
            }

        }
    }

    fun unSaveARealEstate(id: Int) {
        viewModelScope.launch {
            val res = unSaveARealEstateUseCase.unSaveARealEstate(id)
            _data.value = _data.value.map {
                if(it.id==id){
                    it.copy(isSaved = false)
                }else{
                    it
                }
            }

        }
    }
    fun resetData(){
        _data.value = emptyList()
    }
}