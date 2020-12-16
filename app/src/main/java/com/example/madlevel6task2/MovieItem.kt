package com.example.madlevel6task2

import com.google.gson.annotations.SerializedName

data class MovieItem(

    @SerializedName("id") var id: String,
    @SerializedName("backdrop_path") var backdrop: String,
    @SerializedName("poster_path") var poster: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var release: String,
    @SerializedName("rating") var rating: Double,
    @SerializedName("overview") var overview: String
)