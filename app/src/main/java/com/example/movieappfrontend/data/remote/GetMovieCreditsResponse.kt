package com.example.movieappfrontend.data.remote

import com.example.movieappfrontend.data.model.CastPerson
import com.example.movieappfrontend.data.model.CrewPerson
import com.google.gson.annotations.SerializedName

data class GetMovieCreditsResponse(
    @SerializedName("id") val idMovie: String,
    @SerializedName("cast") val castPersonList: List<CastPerson>,
    @SerializedName("crew") val crewPersonList: List<CrewPerson>
)