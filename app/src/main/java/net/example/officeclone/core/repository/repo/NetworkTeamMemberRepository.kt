package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.officeclone.common.data.Result
import net.example.officeclone.common.data.asResult
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

    override fun observeTeamMembers(): Flow<List<Member>> {
        return network.observeTeamMembers().map {
            it.members
        }
    }
}