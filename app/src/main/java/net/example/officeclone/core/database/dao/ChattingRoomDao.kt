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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChattingRooms(chattingRooms: List<ChattingRoomEntity>): List<Long>
}