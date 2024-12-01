package net.example.officeclone.core.network.data

import net.example.officeclone.core.model.Chat

data class NetworkChat(
    val id: String,
    val date: String,
    val message: String,
    val memberId: String
)

fun NetworkChat.asExternal() = Chat(
    id = id,
    date = date,
    message = message,
    memberId = memberId
)