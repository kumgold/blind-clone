package net.example.officeclone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.database.model.ChattingRoomEntity

@Dao
interface ChattingRoomDao {
    @Query(value = "SELECT * FROM chatting_rooms")
    fun getChattingRooms(): Flow<List<ChattingRoomEntity>>

    @Query(value = "SELECT * FROM chatting_rooms WHERE id = :id")
    suspend fun findChattingRoomById(id: String): ChattingRoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRoom(chattingRoomEntity: ChattingRoomEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChattingRooms(chattingRooms: List<ChattingRoomEntity>): List<Long>
}