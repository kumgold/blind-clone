package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.model.Member

interface TeamMemberRepository {
    fun getTeamMembers(): Flow<List<Member>>
}