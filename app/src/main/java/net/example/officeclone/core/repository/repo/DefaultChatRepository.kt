package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.example.officeclone.core.model.Chat
import net.example.officeclone.core.network.data.NetworkChat
import net.example.officeclone.core.network.data.asExternal
import net.example.officeclone.core.network.retrofit.RetrofitOfficeNetwork
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val network: RetrofitOfficeNetwork
) : ChatRepository {
    override fun getChatList(chattingRoomId: String): Flow<List<Chat>> {
        return flow {
            emit(
                network.getChatList(chattingRoomId)
                    .map(NetworkChat::asExternal)
            )
        }
    }
}