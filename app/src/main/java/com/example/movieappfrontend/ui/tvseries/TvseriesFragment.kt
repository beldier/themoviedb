package com.example.movieappfrontend.ui.tvseries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappfrontend.R
import com.example.movieappfrontend.data.model.Movie
import com.example.movieappfrontend.data.repository.MoviesRepository
import com.example.movieappfrontend.data.repository.TvSeriesRepository
import com.example.movieappfrontend.databinding.FragmentHomeBinding
import com.example.movieappfrontend.databinding.FragmentTvseriesBinding
import com.example.movieappfrontend.ui.home.HomeFragmentDirections
import com.example.movieappfrontend.ui.home.MoviesAdapter

class TvseriesFragment : Fragment() {
    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager

    private lateinit var newMovies: RecyclerView
    private lateinit var newMoviesAdapter: MoviesAdapter
    private lateinit var newMoviesLayoutMgr: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTvseriesBinding>(
            inflater, R.layout.fragment_tvseries, container, false
        )

        popularMovies = binding.popularMovies
        popularMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        TvSeriesRepository.getPopularTvSeries(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )

        upcomingMovies = binding.upcomingMovies
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter
        TvSeriesRepository.getTopRatedTvSeries(
            onSuccess = ::onUpcomingMoviesFetched,
            onError = ::onError
        )


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun showMovieDetails(movie: Movie) {

        var genres: List<String> = ArrayList()
        //movie.genres.forEach { genres+= it.name }
        this.view?.findNavController()?.navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                movie
            )
        )
    }

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
        upcomingMoviesAdapter.updateMovies(movies)
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.updateMovies(movies)
    }

    private fun onError() {
        Toast.makeText(context, "ERROR fetching movies", Toast.LENGTH_SHORT).show()
    }
}