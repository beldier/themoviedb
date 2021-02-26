package com.example.movieappfrontend.ui.home

import android.os.Bundle
import android.util.Log
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
import com.example.movieappfrontend.databinding.FragmentHomeBinding
import com.example.movieappfrontend.ui.moviedetail.MovieDetailFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

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
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        popularMovies = binding.popularMovies
        popularMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMoviesAdapter = MoviesAdapter(listOf())
        popularMovies.adapter = popularMoviesAdapter

        MoviesRepository.getPopularMovies(
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
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf())
        upcomingMovies.adapter = upcomingMoviesAdapter
        MoviesRepository.getUpcomingMovies(
            onSuccess = ::onUpcomingMoviesFetched,
            onError = ::onError
        )

        newMovies = binding.newMovies
        newMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        newMovies.layoutManager = newMoviesLayoutMgr
        newMoviesAdapter = MoviesAdapter(mutableListOf())
        newMovies.adapter = newMoviesAdapter

        MoviesRepository.getNewMovies(
            onSuccess = ::onNewMoviesFetched,
            onError = ::onError
        )


        // Inflate the layout for this fragment
        return binding.root
    }
    private fun onNewMoviesFetched(movies: List<Movie>) {
        newMoviesAdapter.updateMovies(movies)
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