package com.example.movieappfrontend.ui.search

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
import com.example.movieappfrontend.data.model.themoviedb.Movie
import com.example.movieappfrontend.data.repository.themoviedb.MoviesRepository
import com.example.movieappfrontend.databinding.FragmentMovieSearchBinding
import com.example.movieappfrontend.ui.home.HomeFragmentDirections
import com.example.movieappfrontend.ui.home.MoviesAdapter


class MovieSearchFragment : Fragment() {

    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieSearchBinding>(
            inflater,
            R.layout.fragment_movie_search,
            container,
            false
        )
        searchMovies = binding.popularMovies
        searchMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val args = MovieSearchFragmentArgs.fromBundle(requireArguments())

        searchMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        searchMovies.adapter = searchMoviesAdapter
        MoviesRepository.fetchSearchMovies(
            onSuccess = ::onSearchMoviesFetched,
            onError = ::onError,
            searchText = args.queryString
        )

        return binding.root
    }

    private fun onSearchMoviesFetched(movies: List<Movie>) {
        searchMoviesAdapter.updateMovies(movies)
    }
    private fun onError() {
        Toast.makeText(context, "ERROR fetching movies", Toast.LENGTH_SHORT).show()
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

}