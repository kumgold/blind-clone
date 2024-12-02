package net.example.officeclone.core.network.data

import net.example.officeclone.core.database.model.ChatEntity

data class NetworkChat(
    val id: String,
    val date: String,
    val message: String,
    val memberId: String,
    val chattingRoomId: String
)

fun NetworkChat.asEntity() = ChatEntity(
    id = id,
    date = date,
    message = message,
    memberId = memberId,
    chattingRoomId = chattingRoomId
)