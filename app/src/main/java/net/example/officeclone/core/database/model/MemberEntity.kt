package net.example.officeclone.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import net.example.officeclone.core.model.Member

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey val id: String,
    val name: String,
    val number: String,
    @ColumnInfo(defaultValue = "")
    val statusMessage: String,
    @ColumnInfo(defaultValue = "")
    val group: String,
    val isFavorite: Boolean
)

fun MemberEntity.asExternal() = Member(
    id = id,
    name = name,
    number = number,
    statusMessage = statusMessage,
    group = group,
    isFavorite = isFavorite
)