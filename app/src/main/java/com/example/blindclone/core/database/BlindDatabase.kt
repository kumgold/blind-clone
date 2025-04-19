package com.example.blindclone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blindclone.core.database.converter.ListStringConverter
import com.example.blindclone.core.database.dao.ChatDao
import com.example.blindclone.core.database.dao.MemberDao
import com.example.blindclone.core.database.model.ChatEntity
import com.example.blindclone.core.database.model.MemberEntity

@Database(
    entities = [
        MemberEntity::class,
        ChatEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
internal abstract class BlindDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun chatDao(): ChatDao
}