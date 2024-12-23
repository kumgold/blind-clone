package net.example.officeclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.officeclone.common.data.Result
import net.example.officeclone.core.database.dao.ChatDao
import net.example.officeclone.core.database.model.ChatEntity
import net.example.officeclone.core.database.model.asExternal
import net.example.officeclone.core.model.Chat
import net.example.officeclone.core.network.data.NetworkChat
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.OfficeWebSocket
import net.example.officeclone.core.network.retrofit.RetrofitOfficeNetwork
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.Calendar
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val network: RetrofitOfficeNetwork,
    private val webSocket: OfficeWebSocket,
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