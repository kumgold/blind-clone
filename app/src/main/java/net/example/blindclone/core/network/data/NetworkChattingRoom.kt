package net.example.blindclone.core.network.data

import net.example.blindclone.core.database.model.ChattingRoomEntity

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
