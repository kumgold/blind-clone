package com.example.blindclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.model.Member
import com.example.blindclone.core.model.Post

interface PostRepository {
    fun getPosts(): Flow<Result<List<Post>>>
}