package net.example.officeclone.core.network.retrofit

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.network.data.TeamResponse

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): TeamResponse

    fun observeTeamMembers(): Flow<TeamResponse>
}