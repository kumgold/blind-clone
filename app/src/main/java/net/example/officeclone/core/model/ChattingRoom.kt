package net.example.officeclone.core.model

import net.example.officeclone.core.network.data.NetworkChattingRoom

data class ChattingRoom(
    val id: String,
    val name: String,
    val previewMessage: String,
    val memberCount: Int
)

fun ChattingRoom.asNetwork() = NetworkChattingRoom(
    id = id,
    name = name,
    previewMessage = previewMessage,
    memberCount = memberCount
)