package com.example.movieappfrontend.data.remote.omdb

import com.example.movieappfrontend.data.model.omdb.Movie
import com.example.movieappfrontend.data.remote.themoviedb.GetMovieCreditsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    fun getMovie(
        @Query("i") idMovie: String,
        @Query("apikey") apiKey: String = api_key
    ): Call<Movie>

    companion object {
        const val api_key = "fce8a115"
    }
}