package com.example.blindclone.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.blindclone.core.database.BlindDatabase
import com.example.blindclone.core.database.dao.MemberDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideMemberDao(
        database: BlindDatabase
    ): MemberDao = database.memberDao()
}