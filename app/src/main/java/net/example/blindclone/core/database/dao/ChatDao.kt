package net.example.blindclone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.example.blindclone.core.database.model.ChatEntity

@Dao
interface ChatDao {
    @Query(value = "SELECT * FROM chats WHERE chattingRoomId = (:chattingRoomId)")
    fun getChatList(chattingRoomId: String): Flow<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatList(chatList: List<ChatEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: ChatEntity): Long
}