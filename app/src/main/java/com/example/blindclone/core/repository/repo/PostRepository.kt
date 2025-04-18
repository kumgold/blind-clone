package com.example.blindclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.model.Member

interface PostRepository {
    fun getTeamMembers(): Flow<Result<List<Member>>>
    suspend fun sync(): Boolean
    suspend fun getMember(memberId: String): Result<Member>
}