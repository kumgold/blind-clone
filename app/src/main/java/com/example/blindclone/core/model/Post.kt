package com.example.blindclone.core.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    val title: String = "",
    val content: String = "",
)
