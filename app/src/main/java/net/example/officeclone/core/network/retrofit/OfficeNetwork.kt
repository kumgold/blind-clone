package net.example.officeclone.core.network.retrofit

import kotlinx.serialization.Serializable
import net.example.officeclone.core.network.data.NetworkChat
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.NetworkMember
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

private interface OfficeNetworkApi {
    @GET("/members")
    suspend fun getTeamMembers(): ApiResponse<List<NetworkMember>>

    @GET("/chatting-rooms")
    suspend fun getChattingRooms(): ApiResponse<List<NetworkChattingRoom>>

    @POST("/chatting-room")
    suspend fun createChattingRoom(
        @Body room: NetworkChattingRoom
    ): NetworkChattingRoom

    @GET("/chats")
    suspend fun getChatList(
        @Query("id") id: String
    ): ApiResponse<List<NetworkChat>>
}

@Singleton
class RetrofitOfficeNetwork @Inject constructor() : OfficeNetworkDataSource {
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
        .create(OfficeNetworkApi::class.java)

    override suspend fun getTeamMembers(): List<NetworkMember> =
        networkApi.getTeamMembers().data

    override suspend fun getChattingRooms(): List<NetworkChattingRoom> =
        networkApi.getChattingRooms().data

    override suspend fun createChattingRoom(room: NetworkChattingRoom): NetworkChattingRoom =
        networkApi.createChattingRoom(room)

    override suspend fun getChatList(chattingRoomId: String): List<NetworkChat> =
        networkApi.getChatList(id = chattingRoomId).data
}

@Serializable
private data class ApiResponse<T>(
    val data: T
)
