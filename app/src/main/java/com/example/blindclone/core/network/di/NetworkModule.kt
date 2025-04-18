package com.example.blindclone.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.blindclone.core.network.retrofit.BlindNetworkDataSource
import com.example.blindclone.core.network.retrofit.BlindWebSocket
import com.example.blindclone.core.network.retrofit.BlindWebSocketDataSource
import com.example.blindclone.core.network.retrofit.RetrofitBlindNetwork

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(impl: RetrofitBlindNetwork): BlindNetworkDataSource

    @Binds
    abstract fun bindsWebSocketDataSource(impl: BlindWebSocket): BlindWebSocketDataSource
}