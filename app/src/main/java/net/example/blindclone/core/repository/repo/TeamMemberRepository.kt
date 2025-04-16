package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.common.data.Result
import net.example.officeclone.core.model.Member

interface TeamMemberRepository {
    fun getTeamMembers(): Flow<Result<List<Member>>>
    suspend fun sync(): Boolean
    suspend fun getMember(memberId: String): Result<Member>
}