package net.example.officeclone.core.network.retrofit

import net.example.officeclone.core.network.data.TeamResponse

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): TeamResponse
}