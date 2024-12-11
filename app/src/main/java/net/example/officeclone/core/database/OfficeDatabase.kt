package net.example.officeclone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.example.officeclone.core.database.converter.ListStringConverter
import net.example.officeclone.core.database.dao.ChatDao
import net.example.officeclone.core.database.dao.ChattingRoomDao
import net.example.officeclone.core.database.dao.MemberDao
import net.example.officeclone.core.database.model.ChatEntity
import net.example.officeclone.core.database.model.ChattingRoomEntity
import net.example.officeclone.core.database.model.MemberEntity

@Database(
    entities = [
        ChattingRoomEntity::class,
        MemberEntity::class,
        ChatEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(ListStringConverter::class)
internal abstract class OfficeDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun chattingRoomDao(): ChattingRoomDao
    abstract fun chatDao(): ChatDao
}