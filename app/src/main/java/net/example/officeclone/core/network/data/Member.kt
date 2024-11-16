package net.example.officeclone.core.network.data

data class Member(
    val id: String,
    val name: String,
    val number: String,
    val statusMessage: String,
    val group: String = "",
    val isFavorite: Boolean = false
)
