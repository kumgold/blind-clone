package net.example.officeclone.core.network.data

data class User(
    val id: String,
    val name: String,
    val number: String,
    val message: String,
    val group: String = "",
    val favorite: Boolean = false
)
