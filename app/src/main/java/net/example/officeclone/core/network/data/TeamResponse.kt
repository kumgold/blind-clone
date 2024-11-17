package net.example.officeclone.core.network.data

data class TeamResponse(
    val statusCode: Int,
    val members: List<Member>
)