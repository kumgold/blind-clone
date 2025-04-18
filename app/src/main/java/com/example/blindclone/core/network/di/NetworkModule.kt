package com.example.blindclone.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.blindclone.core.network.retrofit.OfficeNetworkDataSource
import com.example.blindclone.core.network.retrofit.OfficeWebSocket
import com.example.blindclone.core.network.retrofit.OfficeWebSocketDataSource
import com.example.blindclone.core.network.retrofit.RetrofitOfficeNetwork

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(impl: RetrofitOfficeNetwork): OfficeNetworkDataSource

    @Binds
    abstract fun bindsWebSocketDataSource(impl: OfficeWebSocket): OfficeWebSocketDataSource
}