package com.example.blindclone.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val keyword: String,
    val title: String,
    val content: String,
    val count: Int
)
