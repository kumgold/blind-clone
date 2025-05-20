package com.example.blindclone.core.network.retrofit

import com.example.blindclone.core.network.model.NetworkMember

interface BlindNetworkDataSource {
    suspend fun getTeamMembers(): List<NetworkMember>
}
