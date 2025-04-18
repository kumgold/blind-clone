package com.example.blindclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.blindclone.core.database.dao.ChattingRoomDao
import com.example.blindclone.core.database.model.ChattingRoomEntity
import com.example.blindclone.core.database.model.asExternal
import com.example.blindclone.core.model.ChattingRoom
import com.example.blindclone.core.model.asEntity
import com.example.blindclone.core.model.asNetwork
import com.example.blindclone.core.network.data.NetworkChattingRoom
import com.example.blindclone.core.network.data.asEntity
import com.example.blindclone.core.network.retrofit.RetrofitBlindNetwork
import okio.IOException
import javax.inject.Inject

class DefaultChattingRoomRepository @Inject constructor(
    private val network: RetrofitBlindNetwork,
    private val chattingRoomDao: ChattingRoomDao
) : ChattingRoomRepository {

    override fun getChattingRooms(): Flow<List<ChattingRoom>> =
        chattingRoomDao.getChattingRooms()
            .map { it.map(ChattingRoomEntity::asExternal) }

    override suspend fun createChattingRoom(room: ChattingRoom): Result<Boolean> {
        val entity = chattingRoomDao.findChattingRoomById(room.id)

        return try {
            if (entity == null) {
                val networkChattingRoom = network.createChattingRoom(room.asNetwork())
                chattingRoomDao.insertChattingRoom(networkChattingRoom.asEntity())
            }

            Result.success(true)
        } catch (e: IOException) {
            Log.e("create chatting room", "io error = $e")
            if (entity == null) {
                chattingRoomDao.insertChattingRoom(room.asEntity())
            }
            Result.failure(e)
        } catch (e: Exception) {
            Log.e("create chatting room", "error = $e")
            Result.failure(e)
        }
    }

    override suspend fun sync(): Boolean {
        return try {
            val networkChattingRooms = network.getChattingRooms()

            chattingRoomDao.insertChattingRooms(
                networkChattingRooms.map(NetworkChattingRoom::asEntity)
            )

            true
        } catch (e: Exception) {
            Log.e("chatting room list sync", "error = $e")
            false
        }
    }
}