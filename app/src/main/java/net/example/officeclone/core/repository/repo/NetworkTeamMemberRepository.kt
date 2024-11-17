package net.example.officeclone.core.repository.repo

import net.example.officeclone.core.network.data.Member
import net.example.officeclone.core.network.data.TeamResponse
import net.example.officeclone.core.network.retrofit.OfficeNetworkDataSource
import javax.inject.Inject

class NetworkTeamMemberRepository @Inject constructor(
    private val network: OfficeNetworkDataSource
) : TeamMemberRepository {

    override suspend fun getTeamMembers(): List<Member> {
        return network.getTeamMembers().members
    }
}