package net.example.officeclone.core.repository.repo

import net.example.officeclone.core.network.data.Member

interface TeamMemberRepository {
    suspend fun getTeamMembers(): List<Member>
}