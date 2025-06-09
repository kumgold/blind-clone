package com.example.blindclone.core.repository.di

import com.example.blindclone.core.repository.repo.PostRepository
import com.example.blindclone.core.repository.repo.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPostRepository(
        postRepository: PostRepositoryImpl
    ): PostRepository
}