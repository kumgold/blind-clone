package com.example.blindclone.core.network.retrofit

import com.example.blindclone.core.network.model.NetworkChat
import com.example.blindclone.core.network.model.NetworkMember
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface BlindNetworkApi {
    @GET("/members")
    suspend fun getTeamMembers(): ApiResponse<List<NetworkMember>>

    @GET("/chats")
    suspend fun getChatList(
        @Query("id") id: String
    ): ApiResponse<List<NetworkChat>>

    @POST("/chats")
    suspend fun sendChat(
        @Body chat: NetworkChat
    ): Long
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

    override suspend fun getChatList(chattingRoomId: String): List<NetworkChat> =
        networkApi.getChatList(id = chattingRoomId).data

    override suspend fun sendChat(chat: NetworkChat): Long =
        networkApi.sendChat(chat)
}

@Serializable
private data class ApiResponse<T>(
    val data: T
)
