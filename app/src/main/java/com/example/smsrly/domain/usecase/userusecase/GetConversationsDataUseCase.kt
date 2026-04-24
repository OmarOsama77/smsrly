package com.example.smsrly.domain.usecase.userusecase

import android.util.Log
import com.example.smsrly.data.remote.dto.firebasedtos.ConversationDto
import com.example.smsrly.domain.models.Conversation
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.domain.repository.IFirebaseRepo
import com.example.smsrly.domain.repository.IUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetConversationsDataUseCase @Inject constructor(
    private val firebaseRepo: IFirebaseRepo,
    private val userRepo: IUserRepo
) {

    suspend fun getConversations(userId: Int): Flow<Map<String, Conversation>> {
        return firebaseRepo.getConversations(userId).map { conversationsMap ->
            val ids = conversationsMap.keys.toList()
            getUsersInfo(ids).forEach { data ->
                Log.d("i have some data ",data.toString())
                conversationsMap[data.key]?.receiverInfo = data.value
            }
            Log.d("Before sending ",conversationsMap.toString())
            conversationsMap
        }


    }


    suspend fun getUsersInfo(usersIds: List<String>): Map<String, UserInfo> {
        Log.d("ya omar ","im in")
        val res = userRepo.getUsersInfo(usersIds)
        if (res.isSuccess) {
            return res.getOrNull()!!
        }
        return emptyMap()
    }
}