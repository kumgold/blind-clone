package net.example.officeclone.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.example.officeclone.core.database.model.MemberEntity

@Dao
interface MemberDao {
    @Query(value = "SELECT * FROM members")
    fun getMemberEntities(): Flow<List<MemberEntity>>
}