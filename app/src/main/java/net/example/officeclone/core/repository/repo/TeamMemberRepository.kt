package net.example.officeclone.core.repository.repo

interface TeamMemberRepository {
    suspend fun getTeamMembers(): List<String>
}