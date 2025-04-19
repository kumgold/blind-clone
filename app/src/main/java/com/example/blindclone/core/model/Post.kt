package com.example.blindclone.core.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    val id: String = System.currentTimeMillis().toString(),
    val content: String,
    val timestamp: Long,
    val writer: String
) {

}
