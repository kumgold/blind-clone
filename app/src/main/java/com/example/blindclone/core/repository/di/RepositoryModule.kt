package com.example.blindclone.core.repository.di

import com.example.blindclone.core.repository.repo.ChatRepository
import com.example.blindclone.core.repository.repo.DefaultChatRepository
import com.example.blindclone.core.repository.repo.DefaultMemberRepository
import com.example.blindclone.core.repository.repo.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamMemberRepository(
        teamMemberRepository: DefaultMemberRepository
    ): PostRepository

    @Binds
    abstract fun bindChatRepository(
        chatRepository: DefaultChatRepository
    ): ChatRepository
}