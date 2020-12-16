package com.example.madlevel6task2

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("backdrop") var backdrop: String,
    @SerializedName("poster") var poster: String,
    @SerializedName("title") var title: String,
    @SerializedName("release") var release: String,
    @SerializedName("rating") var rating: Double,
    @SerializedName("overview") var overview: String
)