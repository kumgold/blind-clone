package net.example.officeclone.core.network.retrofit

import net.example.officeclone.core.network.data.NetworkChat
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.NetworkMember
import retrofit2.http.Body

interface OfficeNetworkDataSource {
    suspend fun getTeamMembers(): List<NetworkMember>

    suspend fun getChattingRooms(): List<NetworkChattingRoom>

    suspend fun createChattingRoom(room: NetworkChattingRoom): NetworkChattingRoom

    suspend fun getChatList(chattingRoomId: String): List<NetworkChat>

    suspend fun sendChat(chat: NetworkChat): Long
}
