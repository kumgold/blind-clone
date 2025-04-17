package net.example.blindclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.example.blindclone.common.data.Result
import net.example.blindclone.common.data.asResult
import net.example.blindclone.core.database.dao.MemberDao
import net.example.blindclone.core.database.model.MemberEntity
import net.example.blindclone.core.database.model.asExternal
import net.example.blindclone.core.model.Member
import net.example.blindclone.core.network.data.NetworkMember
import net.example.blindclone.core.network.data.asEntity
import net.example.blindclone.core.network.retrofit.OfficeNetworkDataSource
import javax.inject.Inject

class DefaultMemberRepository @Inject constructor(
    private val network: OfficeNetworkDataSource,
    private val memberDao: MemberDao
) : PostRepository {

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

    override suspend fun getMember(memberId: String): Result<Member> {
        return try {
            Result.Success(memberDao.getMember(memberId).asExternal())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}