package com.example.movieappfrontend.data.repository

import com.example.movieappfrontend.data.model.Movie
import com.example.movieappfrontend.data.remote.ApiService
import com.example.movieappfrontend.data.remote.GetMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvSeriesRepository {

    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    fun getPopularTvSeries(page: Int = 1,
                         onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit) {
        TvSeriesRepository.api.getPopularTvSeries(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedTvSeries(page: Int = 1,
                           onSuccess: (movies: List<Movie>) -> Unit,
                           onError: () -> Unit) {
        TvSeriesRepository.api.getTopRatedTvSeries(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}