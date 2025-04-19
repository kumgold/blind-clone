package com.example.blindclone.core.network.di

import com.example.blindclone.core.network.retrofit.BlindNetworkDataSource
import com.example.blindclone.core.network.retrofit.RetrofitBlindNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(impl: RetrofitBlindNetwork): BlindNetworkDataSource
}