package net.example.officeclone.core.model

data class Chat(
    val id: String,
    val datetimeMilli: Long,
    val message: String,
    val memberId: String
)
