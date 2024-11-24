package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.model.ChattingRoom

interface ChattingRoomRepository {
    fun getChattingRooms(): Flow<List<ChattingRoom>>
    suspend fun createChattingRoom(room: ChattingRoom): Result<Boolean>
    suspend fun sync(): Boolean
}