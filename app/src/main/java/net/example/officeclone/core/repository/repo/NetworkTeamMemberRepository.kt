package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.example.officeclone.core.database.model.asExternal
import net.example.officeclone.core.model.Member
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.OfficeNetworkDataSource
import javax.inject.Inject

class NetworkTeamMemberRepository @Inject constructor(
    private val network: OfficeNetworkDataSource
) : TeamMemberRepository {

    override fun getTeamMembers(): Flow<List<Member>> {
        return flow {
            emit(network.getTeamMembers().map { it.asEntity().asExternal() })
        }
    }
}