package net.example.officeclone.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.example.officeclone.core.database.OfficeDatabase
import net.example.officeclone.core.database.dao.ChattingRoomDao
import net.example.officeclone.core.database.dao.MemberDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideMemberDao(
        database: OfficeDatabase
    ): MemberDao = database.memberDao()

    @Provides
    fun provideChattingRoomDao(
        database: OfficeDatabase
    ): ChattingRoomDao = database.chattingRoomDao()
}