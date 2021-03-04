package com.example.movieappfrontend.ui.moviedetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappfrontend.data.local.dao.MovieDao
import com.example.movieappfrontend.data.model.Movie
import com.example.movieappfrontend.data.repository.MoviesRepository
import java.lang.IllegalArgumentException

class MovieDetailViewModelFactory(private val dataSource: MovieDao, private val application: Application,private val movie: Movie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(dataSource,application,movie) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}