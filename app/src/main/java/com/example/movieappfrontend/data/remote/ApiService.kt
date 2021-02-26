package com.example.movieappfrontend.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "d426eba4a0a3c35c0e882c10af323565",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}