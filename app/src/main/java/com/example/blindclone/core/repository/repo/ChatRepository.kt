package com.example.blindclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.model.Chat

interface ChatRepository {
    fun getChatList(chattingRoomId: String): Flow<List<Chat>>
    suspend fun sync(chattingRoomId: String): Boolean
    suspend fun sendChat(message: String, memberId: String, chattingRoomId: String): Result<Long>
}