package com.example.blindclone.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.blindclone.core.database.model.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<PostEntity>>

    @Query(value = "SELECT * FROM posts WHERE id = :id")
    suspend fun getPost(id: String): PostEntity

    // Refresh -> Delete and Set

}