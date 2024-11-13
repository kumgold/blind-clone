package net.hicare.officeclone.core.network

data class User(
    val id: String,
    val name: String,
    val number: String,
    val message: String = "",
    val group: String = "",
    val isFavorite: Boolean = false
)
