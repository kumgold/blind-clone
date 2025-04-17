package net.example.blindclone.core.network.retrofit

import net.example.blindclone.core.network.data.NetworkChat
import net.example.blindclone.core.network.data.NetworkChattingRoom
import net.example.blindclone.core.network.data.NetworkMember

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): List<NetworkMember>

    suspend fun getChattingRooms(): List<NetworkChattingRoom>

    suspend fun createChattingRoom(room: NetworkChattingRoom): NetworkChattingRoom

    suspend fun getChatList(chattingRoomId: String): List<NetworkChat>

    suspend fun sendChat(chat: NetworkChat): Long
}
