package net.example.officeclone.core.network.retrofit

import retrofit2.http.GET


interface OfficeApi {

    @GET("/team")
    suspend fun getTeamMembers(): List<String>
}
