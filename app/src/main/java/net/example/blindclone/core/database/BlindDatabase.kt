package net.example.blindclone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.example.blindclone.core.database.converter.ListStringConverter
import net.example.blindclone.core.database.dao.ChatDao
import net.example.blindclone.core.database.dao.ChattingRoomDao
import net.example.blindclone.core.database.dao.MemberDao
import net.example.blindclone.core.database.model.ChatEntity
import net.example.blindclone.core.database.model.ChattingRoomEntity
import net.example.blindclone.core.database.model.MemberEntity

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
internal abstract class BlindDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun chattingRoomDao(): ChattingRoomDao
    abstract fun chatDao(): ChatDao
}