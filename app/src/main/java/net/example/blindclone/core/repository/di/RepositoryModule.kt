package net.example.blindclone.core.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.example.blindclone.core.repository.repo.ChatRepository
import net.example.blindclone.core.repository.repo.ChattingRoomRepository
import net.example.blindclone.core.repository.repo.DefaultChatRepository
import net.example.blindclone.core.repository.repo.DefaultChattingRoomRepository
import net.example.blindclone.core.repository.repo.DefaultMemberRepository
import net.example.blindclone.core.repository.repo.TeamMemberRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamMemberRepository(
        teamMemberRepository: DefaultMemberRepository
    ): TeamMemberRepository

    @Binds
    abstract fun bindChattingRoomRepository(
        chattingRoomRepository: DefaultChattingRoomRepository
    ): ChattingRoomRepository

    @Binds
    abstract fun bindChatRepository(
        chatRepository: DefaultChatRepository
    ): ChatRepository
}