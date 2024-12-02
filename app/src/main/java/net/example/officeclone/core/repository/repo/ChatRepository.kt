package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.model.Chat

interface ChatRepository {
    fun getChatList(chattingRoomId: String): Flow<List<Chat>>
    suspend fun sync(chattingRoomId: String): Boolean
}