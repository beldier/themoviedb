package com.example.movieappfrontend.data.model.themoviedb

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    val id: Int? = null,
    val name: String
): Parcelable