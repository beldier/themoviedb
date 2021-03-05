package com.example.movieappfrontend.data.repository.omdb

import android.util.Log
import com.example.movieappfrontend.data.model.omdb.Movie
import com.example.movieappfrontend.data.model.themoviedb.MovieCredits
import com.example.movieappfrontend.data.remote.omdb.ApiService
import com.example.movieappfrontend.data.remote.themoviedb.GetMovieCreditsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {

    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(
            ApiService::class.java
        )


    }

    fun fetchMovie(
        onSuccess: (movie: Movie) -> Unit,
        onError: () -> Unit,
        id: String
    ) {

        api.getMovie(idMovie = id)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(
                                responseBody
                            )
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


}