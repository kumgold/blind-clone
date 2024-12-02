package net.example.officeclone.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.example.officeclone.core.model.Chat

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey val id: String,
    val date: String,
    val message: String,
    val memberId: String,
    val chattingRoomId: String
)

fun ChatEntity.asExternal() = Chat(
    id = id,
    date = date,
    message = message,
    memberId = memberId
)