package net.example.officeclone.core.network.retrofit

import net.example.officeclone.core.network.data.TeamResponse
import retrofit2.http.GET


interface OfficeApi {

    @GET("/team")
    suspend fun getTeamMembers(): TeamResponse
}
