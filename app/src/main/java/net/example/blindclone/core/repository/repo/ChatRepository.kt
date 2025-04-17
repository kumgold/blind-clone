package net.example.blindclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.blindclone.common.data.Result
import net.example.blindclone.core.model.Chat

interface ChatRepository {
    fun getChatList(chattingRoomId: String): Flow<List<Chat>>
    suspend fun sync(chattingRoomId: String): Boolean
    suspend fun sendChat(message: String, memberId: String, chattingRoomId: String): Result<Long>
}