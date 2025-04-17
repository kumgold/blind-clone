package net.example.blindclone.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.example.blindclone.core.model.Chat

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey val id: String,
    val datetimeMilli: Long,
    val message: String,
    val memberId: String,
    val chattingRoomId: String
)

fun ChatEntity.asExternal() = Chat(
    id = id,
    datetimeMilli = datetimeMilli,
    message = message,
    memberId = memberId
)