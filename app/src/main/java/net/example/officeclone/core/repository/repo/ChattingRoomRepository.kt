package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.database.model.ChattingRoomEntity

interface ChattingRoomRepository {
    fun getChattingRooms(): Flow<List<ChattingRoomEntity>>
    suspend fun sync(): Boolean
}