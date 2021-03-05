package com.example.movieappfrontend.data.remote.themoviedb

import com.example.movieappfrontend.data.model.themoviedb.CastPerson
import com.example.movieappfrontend.data.model.themoviedb.CrewPerson
import com.google.gson.annotations.SerializedName

data class GetMovieCreditsResponse(
    @SerializedName("id") val idMovie: String,
    @SerializedName("cast") val castPersonList: List<CastPerson>,
    @SerializedName("crew") val crewPersonList: List<CrewPerson>
)