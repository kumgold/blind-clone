package net.example.officeclone.core.repository.repo

import net.example.officeclone.core.network.retrofit.OfficeNetworkDataSource
import javax.inject.Inject

class NetworkTeamMemberRepository @Inject constructor(
    private val network: OfficeNetworkDataSource
) : TeamMemberRepository {

    override suspend fun getTeamMembers(): List<String> {
        return network.getTeamMembers()
    }
}