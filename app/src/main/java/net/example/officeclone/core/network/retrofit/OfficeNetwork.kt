package net.example.officeclone.core.network.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.example.officeclone.core.network.data.TeamResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitOfficeNetwork @Inject constructor() : OfficeNetworkDataSource {
    private val api = Retrofit.Builder()
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
        .create(OfficeApi::class.java)

    override suspend fun getTeamMembers(): TeamResponse = api.getTeamMembers()

    override fun observeTeamMembers(): Flow<TeamResponse> = flow { api.getTeamMembers() }
}

