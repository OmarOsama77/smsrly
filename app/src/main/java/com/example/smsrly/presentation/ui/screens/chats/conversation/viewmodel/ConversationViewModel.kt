package com.example.smsrly.presentation.ui.screens.chats.conversation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smsrly.domain.models.Message
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.usecase.chattingusecases.ReadConversationUseCase
import com.example.smsrly.domain.usecase.chattingusecases.SendAMessageUseCase
import com.example.smsrly.domain.usecase.userusecase.GetUserFlowUseCase
import com.example.smsrly.presentation.ui.screens.home.viewmodel.states.UserState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val sendMessageUseCase: SendAMessageUseCase,
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private val readConversationUseCase: ReadConversationUseCase,
    
) : ViewModel() {

    val user: StateFlow<User?> = getUserFlowUseCase.getUser()
    fun sendMessage(receiverId: Int, text: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendAMessage(user.value!!.userId!!, receiverId, text)
        }
    }
    private val _conversations = MutableStateFlow<List<Message>>(emptyList())
    val conversation: StateFlow<List<Message>> = _conversations



    fun readAConversation(senderId:Int,receiverId: Int){

       viewModelScope.launch {
           readConversationUseCase.readAConversation(senderId,receiverId).collect { messages ->
               _conversations.value = messages
           }
       }

    }


}
