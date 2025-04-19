package com.example.blindclone.core.network.model

import com.example.blindclone.core.database.model.MemberEntity

data class NetworkMember(
    val id: String,
    val name: String,
    val number: String,
    val statusMessage: String,
    val group: String = "",
    val isFavorite: Boolean = false
)

fun NetworkMember.asEntity() = MemberEntity(
    id = id,
    name = name,
    number = number,
    statusMessage = statusMessage,
    group = group,
    isFavorite = isFavorite
)