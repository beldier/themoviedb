package com.example.movieappfrontend.data.remote.themoviedb

import com.example.movieappfrontend.data.model.themoviedb.Movie
import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)