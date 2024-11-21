package net.example.officeclone.core.model

data class ChattingRoom(
    val id: String,
    val name: String,
    val previewMessage: String,
    val memberCount: Int
)