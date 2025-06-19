package ru.hse.financialdetective.core.data.network

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST()
    suspend fun registerUser(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("username") username: String,
    ): Response<String>


}