package net.example.blindclone.core.model

data class Member(
    val id: String,
    val name: String,
    val number: String,
    val statusMessage: String,
    val group: String = "",
    val isFavorite: Boolean = false
)