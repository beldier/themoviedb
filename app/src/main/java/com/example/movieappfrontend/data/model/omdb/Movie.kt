package com.example.movieappfrontend.data.model.omdb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("Title") val title : String,
    @SerializedName("Year") val year : Int,
    @SerializedName("Rated") val rated : String,
    @SerializedName("Released") val released : String,
    @SerializedName("Runtime") val runtime : String,
    @SerializedName("Genre") val genre : String,
    @SerializedName("Director") val director : String,
    @SerializedName("Writer") val writer : String,
    @SerializedName("Actors") val actors : String,
    @SerializedName("Plot") val plot : String,
    @SerializedName("Language") val language : String,
    @SerializedName("Country") val country : String,
    @SerializedName("Awards") val awards : String,
    @SerializedName("Poster") val poster : String,
    @SerializedName("Ratings") val ratings : List<Ratings>,
    @SerializedName("Metascore") val metascore : String,
    @SerializedName("imdbRating") val imdbRating : Double,
    @SerializedName("imdbVotes") val imdbVotes : String,
    @SerializedName("imdbID") val imdbID : String,
    @SerializedName("Type") val type : String,
    @SerializedName("DVD") val dVD : String,
    @SerializedName("BoxOffice") val boxOffice : String,
    @SerializedName("Production") val production : String,
    @SerializedName("Website") val website : String,
    @SerializedName("Response") val response : Boolean
) :Parcelable