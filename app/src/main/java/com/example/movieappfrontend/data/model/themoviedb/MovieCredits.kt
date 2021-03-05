package com.example.movieappfrontend.data.model.themoviedb

import android.os.Parcelable
import com.example.movieappfrontend.data.model.themoviedb.CastPerson
import com.example.movieappfrontend.data.model.themoviedb.CrewPerson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCredits(
    val id: String = "",
    val castLists: List<CastPerson>? = ArrayList(),
    val crewList: List<CrewPerson>? = ArrayList()
) : Parcelable