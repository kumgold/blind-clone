package net.example.officeclone.core.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.example.officeclone.core.repository.repo.NetworkTeamMemberRepository
import net.example.officeclone.core.repository.repo.TeamMemberRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamMemberRepository(
        teamMemberRepository: NetworkTeamMemberRepository
    ): TeamMemberRepository
}