package com.example.madlevel6task2

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface MovieApiService{

    @GET("/discover/movie?api_key=a7805885a616a035ea480e207a263ef3")
    suspend fun getList(): Response


    data class Response(
            @SerializedName ("results") var results: List<MovieItem>
    )
}