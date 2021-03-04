package com.example.movieappfrontend.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappfrontend.data.model.Movie
import java.lang.IllegalArgumentException

class MovieDetailViewModelFactory(private val movie: Movie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}