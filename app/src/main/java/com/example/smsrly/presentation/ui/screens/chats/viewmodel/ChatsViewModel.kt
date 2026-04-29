package com.example.smsrly.presentation.ui.screens.chats.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.domain.observer.IConnectivityObserver
import com.example.smsrly.domain.usecase.networkobserverusecases.NetworkObserverUseCase
import com.example.smsrly.domain.usecase.userusecase.GetConversationsDataUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.example.smsrly.presentation.ui.screens.chats.viewmodel.states.ChatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private val getConversationsDataUseCase: GetConversationsDataUseCase,
    private val networkStatusUseCase: NetworkObserverUseCase

) : ViewModel() {


    val user = getUserFlowUseCase.getUser()

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations

    private val _state = MutableStateFlow<ChatsState>(ChatsState.Idle)
    val state: StateFlow<ChatsState> = _state

    private val _networkStatus = MutableStateFlow(IConnectivityObserver.Status.UnAvailable)
    val networkStatus : StateFlow<IConnectivityObserver.Status> = _networkStatus

    init {
        getNetworkFlow()
        getConversationUsersData()
    }

    fun getConversationUsersData() {

        viewModelScope.launch {
            _state.value = ChatsState.Loading
            getConversationsDataUseCase.getConversations(user.value!!.userId!!).collect { data ->
                _conversations.value = data.values.toList()
                _state.value = ChatsState.Success
            }

        }
    }

    fun getNetworkFlow(){
        viewModelScope.launch {
            networkStatusUseCase.invoke().collect {
                _networkStatus.value = it
            }
        }
    }
}