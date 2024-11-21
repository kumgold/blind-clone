package net.example.officeclone.core.network.data

import net.example.officeclone.core.database.model.ChattingRoomEntity

data class NetworkChattingRoom(
    val id: String,
    val name: String,
    val previewMessage: String,
    val memberCount: Int
)

fun NetworkChattingRoom.asEntity() = ChattingRoomEntity(
    id = id,
    name = name,
    previewMessage = previewMessage,
    memberCount = memberCount
)
