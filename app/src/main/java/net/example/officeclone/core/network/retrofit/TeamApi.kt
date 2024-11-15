package net.example.officeclone.core.network.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton


private interface TeamApi {

    @GET("/team")
    suspend fun getMembers(): List<String>
}

@Singleton
internal class OfficeNetworkDataSource() {
    private val api = Retrofit.Builder()
        .baseUrl("localhost:8080")
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
        .create(TeamApi::class.java)
}