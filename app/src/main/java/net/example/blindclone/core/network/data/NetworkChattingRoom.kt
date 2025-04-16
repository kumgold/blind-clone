package net.example.officeclone.core.network.data

import net.example.officeclone.core.database.model.ChattingRoomEntity

data class NetworkChattingRoom(
    val id: String,
    val name: String,
    val memberIdList: List<String>
)

fun NetworkChattingRoom.asEntity() = ChattingRoomEntity(
    id = id,
    name = name,
    memberIdList = memberIdList
)
