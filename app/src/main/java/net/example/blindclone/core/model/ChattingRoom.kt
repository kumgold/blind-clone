package net.example.blindclone.core.model

import net.example.blindclone.core.database.model.ChattingRoomEntity
import net.example.blindclone.core.network.data.NetworkChattingRoom

data class ChattingRoom(
    val id: String,
    val name: String,
    val memberIdList: List<String>
)

fun ChattingRoom.asNetwork() = NetworkChattingRoom(
    id = id,
    name = name,
    memberIdList = memberIdList
)

fun ChattingRoom.asEntity() = ChattingRoomEntity(
    id = id,
    name = name,
    memberIdList = memberIdList
)