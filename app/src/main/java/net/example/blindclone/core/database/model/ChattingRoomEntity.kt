package net.example.officeclone.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.example.officeclone.core.model.ChattingRoom

@Entity(tableName = "chatting_rooms")
data class ChattingRoomEntity(
    @PrimaryKey val id: String,
    val name: String,
    val memberIdList: List<String>
)

fun ChattingRoomEntity.asExternal() = ChattingRoom(
    id = id,
    name = name,
    memberIdList = memberIdList
)