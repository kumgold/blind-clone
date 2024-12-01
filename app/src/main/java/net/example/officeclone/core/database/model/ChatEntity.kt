package net.example.officeclone.core.database.model

data class ChatEntity(
    val id: String,
    val date: String,
    val message: String,
    val memberId: String
)
