package net.example.blindclone.core.network.data

import net.example.blindclone.core.database.model.ChatEntity

data class NetworkChat(
    val id: String,
    val datetimeMilli: Long,
    val message: String,
    val memberId: String,
    val chattingRoomId: String
)

fun NetworkChat.asEntity() = ChatEntity(
    id = id,
    datetimeMilli = datetimeMilli,
    message = message,
    memberId = memberId,
    chattingRoomId = chattingRoomId
)