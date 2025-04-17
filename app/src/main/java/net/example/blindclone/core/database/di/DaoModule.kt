package net.example.blindclone.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.example.blindclone.core.database.BlindDatabase
import net.example.blindclone.core.database.dao.ChatDao
import net.example.blindclone.core.database.dao.ChattingRoomDao
import net.example.blindclone.core.database.dao.MemberDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideMemberDao(
        database: BlindDatabase
    ): MemberDao = database.memberDao()

    @Provides
    fun provideChattingRoomDao(
        database: BlindDatabase
    ): ChattingRoomDao = database.chattingRoomDao()

    @Provides
    fun provideChatDao(
        database: BlindDatabase
    ): ChatDao = database.chatDao()
}