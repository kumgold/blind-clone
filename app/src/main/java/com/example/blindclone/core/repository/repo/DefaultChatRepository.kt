package com.example.blindclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.database.dao.ChatDao
import com.example.blindclone.core.database.model.ChatEntity
import com.example.blindclone.core.database.model.asExternal
import com.example.blindclone.core.model.Chat
import com.example.blindclone.core.network.data.NetworkChat
import com.example.blindclone.core.network.data.asEntity
import com.example.blindclone.core.network.retrofit.BlindWebSocket
import com.example.blindclone.core.network.retrofit.RetrofitBlindNetwork
import java.util.Calendar
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val network: RetrofitBlindNetwork,
    private val webSocket: BlindWebSocket,
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

    override suspend fun sendChat(
        message: String,
        memberId: String,
        chattingRoomId: String
    ): Result<Long> {
        val c = Calendar.getInstance()
        val chat = NetworkChat(
            id = System.currentTimeMillis().toString(),
            message = message,
            datetimeMilli = c.timeInMillis,
            memberId = memberId,
            chattingRoomId = chattingRoomId
        )

        webSocket.connect()

        webSocket.sendMessage(message)

        return try {
            chatDao.insertChat(chat.asEntity())
            Result.Success(network.sendChat(chat))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}