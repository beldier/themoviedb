package com.example.movieappfrontend.ui.moviedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappfrontend.data.local.dao.MovieDao
import com.example.movieappfrontend.data.model.themoviedb.CrewPerson
import com.example.movieappfrontend.data.model.themoviedb.Movie
import com.example.movieappfrontend.data.model.themoviedb.MovieCredits
import com.example.movieappfrontend.data.repository.themoviedb.MoviesRepository
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

    private val _director = MutableLiveData<String>()
    val director :  LiveData<String>
        get() = _director

    private val _genres = MutableLiveData<String>()
    val genres : LiveData<String>
        get() = _genres

    private val _cast = MutableLiveData<String>()
    val cast :LiveData<String>
        get() = _cast
    init {
        _isFavourite.value = false
        _director.value = ""

        movie.title?.let { Log.i("moviedetail", it) }
        MoviesRepository.getMovieCredits(
            onSuccess = ::onMovieCreditsFetched,
            onError = ::onError,
            id = localMovie.id
        )
        MoviesRepository.fetchMovieById(
            onSuccess = ::onMovieByIDFectched,
            onError = ::onError,
            id = localMovie.id
        )

        initializeMovie()
    }

    private fun onMovieByIDFectched(movie : com.example.movieappfrontend.data.model.themoviedb.Movie)
    {
        movie.imdb_id?.let { Log.i("movieDetails", it) }
        movie.imdb_id?.let {
            com.example.movieappfrontend.data.repository.omdb.MoviesRepository.fetchMovie(
                onSuccess = ::onMovieOmdbFectched,
                onError = ::onError,
                id = it
            )
        }


    }

    private fun onMovieOmdbFectched(movie: com.example.movieappfrontend.data.model.omdb.Movie) {
        _genres.value = movie.genre
        _cast.value = movie.actors
    }

    private fun onMovieCreditsFetched(movieCredits: MovieCredits) {
        var crewPersonList = movieCredits.crewList
        var movieDirector: CrewPerson? = crewPersonList?.firstOrNull{ it.known_for_department == "Directing" && it.job =="Director" }
        if (movieDirector != null) {
            _director.value = movieDirector.name
        }
    }
    private fun onError() {
        Log.i( "movieDetail","ERROR fetching movies")
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