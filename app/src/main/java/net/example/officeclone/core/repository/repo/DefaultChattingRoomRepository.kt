package net.example.officeclone.core.repository.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.officeclone.core.database.dao.ChattingRoomDao
import net.example.officeclone.core.database.model.ChattingRoomEntity
import net.example.officeclone.core.database.model.asExternal
import net.example.officeclone.core.model.ChattingRoom
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.RetrofitOfficeNetwork
import javax.inject.Inject

class DefaultChattingRoomRepository @Inject constructor(
    private val network: RetrofitOfficeNetwork,
    private val chattingRoomDao: ChattingRoomDao
) : ChattingRoomRepository {

    override fun getChattingRooms(): Flow<List<ChattingRoom>> =
        chattingRoomDao.getChattingRooms()
            .map { it.map(ChattingRoomEntity::asExternal) }


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