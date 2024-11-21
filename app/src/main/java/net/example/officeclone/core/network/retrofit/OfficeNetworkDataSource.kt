package net.example.officeclone.core.network.retrofit

import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.NetworkMember

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): List<NetworkMember>

    suspend fun getChattingRooms(): List<NetworkChattingRoom>
}