package net.example.officeclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.officeclone.core.database.dao.ChatDao
import net.example.officeclone.core.database.model.ChatEntity
import net.example.officeclone.core.database.model.asExternal
import net.example.officeclone.core.model.Chat
import net.example.officeclone.core.network.data.NetworkChat
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.RetrofitOfficeNetwork
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val network: RetrofitOfficeNetwork,
    private val chatDao: ChatDao
) : ChatRepository {
    override fun getChatList(chattingRoomId: String): Flow<List<Chat>> {
        return chatDao.getChatList(chattingRoomId)
            .map { it.map(ChatEntity::asExternal) }
    }

    override suspend fun sync(chattingRoomId: String): Boolean {
        return try {
            val networkChatList = network.getChatList(chattingRoomId)

            chatDao.insertChatList(
                networkChatList.map(NetworkChat::asEntity)
            )

            true
        } catch (e: Exception) {
            Log.e("get chat list sync", "error = $e")
            false
        }
    }
}