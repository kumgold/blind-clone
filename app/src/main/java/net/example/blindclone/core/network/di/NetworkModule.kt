package net.example.blindclone.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.example.blindclone.core.network.retrofit.OfficeNetworkDataSource
import net.example.blindclone.core.network.retrofit.OfficeWebSocket
import net.example.blindclone.core.network.retrofit.OfficeWebSocketDataSource
import net.example.blindclone.core.network.retrofit.RetrofitOfficeNetwork

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(impl: RetrofitOfficeNetwork): OfficeNetworkDataSource

    @Binds
    abstract fun bindsWebSocketDataSource(impl: OfficeWebSocket): OfficeWebSocketDataSource
}