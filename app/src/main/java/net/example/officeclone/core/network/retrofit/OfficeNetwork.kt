package net.example.officeclone.core.network.retrofit

import kotlinx.serialization.Serializable
import net.example.officeclone.core.network.data.NetworkChattingRoom
import net.example.officeclone.core.network.data.NetworkMember
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface OfficeNetworkApi {
    @GET("/members")
    suspend fun getTeamMembers(): ApiResponse<List<NetworkMember>>

    @GET("/chattingrooms")
    suspend fun getChattingRooms(): ApiResponse<List<NetworkChattingRoom>>
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
}

@Serializable
private data class ApiResponse<T>(
    val data: T
)
