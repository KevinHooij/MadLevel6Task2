package com.example.madlevel6task2

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService{

    @GET("/3/discover/movie?api_key=a7805885a616a035ea480e207a263ef3")
    suspend fun getList(@Query("primary_release_year") year: Int): Response


    @GET("/3/movie/{movie_id}?api_key=a7805885a616a035ea480e207a263ef3&language=en-US")
    suspend fun getDetails(
        @Path("movie_id") id : Int
    ): MovieItem



    data class Response(
            @SerializedName ("results") var results: List<MovieItem>
    )
}