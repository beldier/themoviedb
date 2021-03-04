package com.example.movieappfrontend.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel : ViewModel() {
    private val _isFavourite =  MutableLiveData<Boolean>()
    val isFavourite :LiveData<Boolean>
        get() = _isFavourite

    init {
        _isFavourite.value = false
    }
    fun clickFavouriteButton()
    {
        _isFavourite.value = (_isFavourite.value)?.not()
    }
}