package com.example.blindclone.core.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    val id: String = "",
    val keyword: String = "",
    val title: String = "",
    val content: String = "",
) {
    override fun toString(): String {
        return "id = $id, keyword = $keyword, title = $title, content = $content"
    }
}
