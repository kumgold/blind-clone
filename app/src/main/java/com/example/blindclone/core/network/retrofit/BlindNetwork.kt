package com.example.blindclone.core.network.retrofit

import com.example.blindclone.core.network.model.NetworkMember
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface BlindNetworkApi {
    @GET("/members")
    suspend fun getTeamMembers(): ApiResponse<List<NetworkMember>>
}

@Singleton
class RetrofitBlindNetwork @Inject constructor() : BlindNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BlindNetworkApi::class.java)

    override suspend fun getTeamMembers(): List<NetworkMember> =
        networkApi.getTeamMembers().data
}

@Serializable
private data class ApiResponse<T>(
    val data: T
)
