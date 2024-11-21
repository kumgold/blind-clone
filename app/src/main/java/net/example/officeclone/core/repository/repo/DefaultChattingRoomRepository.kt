package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.database.dao.ChattingRoomDao
import net.example.officeclone.core.database.model.ChattingRoomEntity
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.RetrofitOfficeNetwork
import javax.inject.Inject

class DefaultChattingRoomRepository @Inject constructor(
    private val network: RetrofitOfficeNetwork,
    private val chattingRoomDao: ChattingRoomDao
) : ChattingRoomRepository {

    override fun getChattingRooms(): Flow<List<ChattingRoomEntity>> =
        chattingRoomDao.getChattingRooms()

    override suspend fun sync(): Boolean {
        return try {
            val networkChattingRooms = network.getChattingRooms()

            chattingRoomDao.insertChattingRooms(
                networkChattingRooms.map(NetworkChattingRoom::asEntity)
            )

            true
        } catch (e: Exception) {
            false
        }
    }
}