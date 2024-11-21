package net.example.officeclone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.example.officeclone.core.database.dao.ChattingRoomDao
import net.example.officeclone.core.database.dao.MemberDao
import net.example.officeclone.core.database.model.ChattingRoomEntity
import net.example.officeclone.core.database.model.MemberEntity

@Database(
    entities = [
        ChattingRoomEntity::class,
        MemberEntity::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class OfficeDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun chattingRoomDao(): ChattingRoomDao
}