package com.example.movieappfrontend.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = api_key,
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = api_key,
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    companion object {
        const val api_key ="d426eba4a0a3c35c0e882c10af323565"
    }
}