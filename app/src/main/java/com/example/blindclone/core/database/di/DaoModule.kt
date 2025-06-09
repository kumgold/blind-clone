package com.example.blindclone.core.database.di

import com.example.blindclone.core.database.BlindDatabase
import com.example.blindclone.core.database.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providePostDao(
        database: BlindDatabase
    ): PostDao = database.postDao()
}