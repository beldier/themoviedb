package com.example.movieappfrontend.data.model.themoviedb

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastPerson(
    val adult: Boolean? = false,
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double? = 0.0,
    val profile_path: String? = null,
    val credit_id: String? = null,
    val cast_id: Int? = 0,
    val character: String? = null,
    val order: Int? = 0
) : Parcelable