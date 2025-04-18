package com.example.blindclone.core.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.blindclone.core.repository.repo.ChatRepository
import com.example.blindclone.core.repository.repo.ChattingRoomRepository
import com.example.blindclone.core.repository.repo.DefaultChatRepository
import com.example.blindclone.core.repository.repo.DefaultChattingRoomRepository
import com.example.blindclone.core.repository.repo.DefaultMemberRepository
import com.example.blindclone.core.repository.repo.PostRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamMemberRepository(
        teamMemberRepository: DefaultMemberRepository
    ): PostRepository

    @Binds
    abstract fun bindChattingRoomRepository(
        chattingRoomRepository: DefaultChattingRoomRepository
    ): ChattingRoomRepository

    @Binds
    abstract fun bindChatRepository(
        chatRepository: DefaultChatRepository
    ): ChatRepository
}