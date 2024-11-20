package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.network.data.Member

interface TeamMemberRepository {
    suspend fun getTeamMembers(): List<Member>

    fun observeTeamMembers(): Flow<List<Member>>
}