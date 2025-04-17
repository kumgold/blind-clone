package net.example.blindclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.blindclone.common.data.Result
import net.example.blindclone.core.model.Member

interface TeamMemberRepository {
    fun getTeamMembers(): Flow<Result<List<Member>>>
    suspend fun sync(): Boolean
    suspend fun getMember(memberId: String): Result<Member>
}