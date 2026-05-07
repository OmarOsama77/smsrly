package com.example.smsrly.presentation.ui.screens.chats.conversation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.Message
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.chattingusecases.ReadConversationUseCase
import com.example.smsrly.domain.usecase.chattingusecases.SendAMessageUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val sendMessageUseCase: SendAMessageUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val readConversationUseCase: ReadConversationUseCase,

    ) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        getUser()
    }
    fun getUser(){
        viewModelScope.launch {
            getUserDataUseCase().collect {user->
                _user.value = user
            }
        }
    }

    fun sendMessage(receiverId: Int, text: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendAMessage(_user.value!!.userId!!, receiverId, text)
        }
    }

    private val _conversations = MutableStateFlow<List<Message>>(emptyList())
    val conversation: StateFlow<List<Message>> = _conversations


    fun readAConversation(senderId: Int, receiverId: Int) {

        viewModelScope.launch {
            readConversationUseCase.readAConversation(senderId, receiverId).collect { messages ->
                _conversations.value = messages
            }
        }

    }


}
