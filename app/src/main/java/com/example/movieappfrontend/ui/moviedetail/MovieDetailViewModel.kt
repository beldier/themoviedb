package com.example.movieappfrontend.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel : ViewModel() {
    var isFavourite =  MutableLiveData<Boolean>()

    init {
        isFavourite.value = false
    }
    fun clickFavouriteButton()
    {
        isFavourite.value = (isFavourite.value)?.not()
    }
}