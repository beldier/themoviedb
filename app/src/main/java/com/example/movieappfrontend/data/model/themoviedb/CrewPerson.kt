package com.example.movieappfrontend.data.model.themoviedb

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CrewPerson(
    val adult: Boolean? = false,
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double? = 0.0,
    val profile_path: String? = null,
    val credit_id: String? = null,
    val department: String? = null,
    val job: String? = null
) : Parcelable