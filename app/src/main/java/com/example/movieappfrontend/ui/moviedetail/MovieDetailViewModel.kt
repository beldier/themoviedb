package com.example.movieappfrontend.ui.moviedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappfrontend.data.local.dao.MovieDao
import com.example.movieappfrontend.data.model.Movie
import kotlinx.coroutines.*

class MovieDetailViewModel(
    val database: MovieDao, application: Application, movie: Movie
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val localMovie = movie

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    init {
        _isFavourite.value = false
        movie.title?.let { Log.i("moviedetail", it) }
        initializeMovie()
    }

    fun clickFavouriteButton() {
        uiScope.launch {
            if(isFavourite.value!!) {
                localMovie.isFavorite = false
                deleteFavourite(localMovie)
                _isFavourite.value = false
            }
            else{
                localMovie.isFavorite = true
                insertFavourite(localMovie)
                _isFavourite.value = true
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



    private fun initializeMovie() {
        uiScope.launch {
            if(getMovieFromDatabase() != null)
                _isFavourite.value = getMovieFromDatabase().isFavorite
        }
    }
    private suspend fun getMovieFromDatabase(): Movie {
        return withContext(Dispatchers.IO){
            var movie = database.getMovie(localMovie.id)
            movie
        }
    }

    private suspend fun insertFavourite(newFavouriteMovie: Movie) {
        withContext(Dispatchers.IO){
            database.insert(newFavouriteMovie)
        }
    }
    private suspend fun deleteFavourite(oldFavouriteMovie: Movie){
        withContext(Dispatchers.IO){
            database.deleteMovie(oldFavouriteMovie.id)
        }
    }

}