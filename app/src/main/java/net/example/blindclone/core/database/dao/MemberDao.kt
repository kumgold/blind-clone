package net.example.officeclone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.database.model.MemberEntity

@Dao
interface MemberDao {
    @Query(value = "SELECT * FROM members")
    fun getMemberEntities(): Flow<List<MemberEntity>>

    @Query(value = "SELECT * FROM members WHERE id = :id")
    suspend fun getMember(id: String): MemberEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMembers(members: List<MemberEntity>): List<Long>
}