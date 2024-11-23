package net.example.officeclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.officeclone.common.data.Result
import net.example.officeclone.common.data.asResult
import net.example.officeclone.core.database.dao.MemberDao
import net.example.officeclone.core.database.model.MemberEntity
import net.example.officeclone.core.database.model.asExternal
import net.example.officeclone.core.model.Member
import net.example.officeclone.core.network.data.NetworkMember
import net.example.officeclone.core.network.data.asEntity
import net.example.officeclone.core.network.retrofit.OfficeNetworkDataSource
import javax.inject.Inject

class DefaultMemberRepository @Inject constructor(
    private val network: OfficeNetworkDataSource,
    private val memberDao: MemberDao
) : TeamMemberRepository {

    override fun getTeamMembers(): Flow<Result<List<Member>>> =
        memberDao.getMemberEntities()
            .map { it.map(MemberEntity::asExternal) }
            .asResult()

    override suspend fun sync(): Boolean {
        return try {
            val networkMembers = network.getTeamMembers()

            memberDao.insertMembers(
                networkMembers.map(NetworkMember::asEntity)
            )

            true
        } catch (e: Exception) {
            Log.e("team member sync", "error = $e")
            false
        }
    }
}