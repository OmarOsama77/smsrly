package com.example.smsrly.presentation.ui.screens.chats.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.data.remote.dto.firebasedtos.MessageDto
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getUserFlowUseCase: GetUserFlowUseCase,
) : ViewModel() {
    private val db = FirebaseDatabase.getInstance().reference
    val user = getUserFlowUseCase.getUser()

    fun startAConversation() {

        viewModelScope.launch {
            try{

            }catch (e : Exception){
                Log.d("uasihoa   ",e.toString())
            }
        }
    }
}