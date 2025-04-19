package com.example.blindclone.core.network.retrofit

import com.example.blindclone.core.network.model.NetworkChat
import com.example.blindclone.core.network.model.NetworkMember

interface BlindNetworkDataSource {
    suspend fun getTeamMembers(): List<NetworkMember>

    suspend fun getChatList(chattingRoomId: String): List<NetworkChat>

    suspend fun sendChat(chat: NetworkChat): Long
}
