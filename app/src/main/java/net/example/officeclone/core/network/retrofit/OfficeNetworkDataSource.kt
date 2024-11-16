package net.example.officeclone.core.network.retrofit

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): List<String>
}