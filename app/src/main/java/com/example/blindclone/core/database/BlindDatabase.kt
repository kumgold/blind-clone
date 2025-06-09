package com.example.blindclone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blindclone.core.database.converter.ListStringConverter
import com.example.blindclone.core.database.dao.PostDao
import com.example.blindclone.core.database.model.PostEntity

@Database(
    entities = [
        PostEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
internal abstract class BlindDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}