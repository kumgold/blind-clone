package com.example.blindclone.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListStringConverter {
    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        return Gson().fromJson(value, Array<String>::class.java)?.toList()
    }
}