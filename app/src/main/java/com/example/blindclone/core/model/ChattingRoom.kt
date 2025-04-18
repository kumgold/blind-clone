package com.example.blindclone.core.model

import com.example.blindclone.core.database.model.ChattingRoomEntity
import com.example.blindclone.core.network.data.NetworkChattingRoom

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