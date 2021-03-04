package com.example.movieappfrontend.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCredits(
    val id: String = "",
    val castLists: List<CastPerson>? = ArrayList(),
    val crewList: List<CrewPerson>? = ArrayList()
) : Parcelable