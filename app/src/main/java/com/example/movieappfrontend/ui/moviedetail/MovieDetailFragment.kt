package com.example.movieappfrontend.ui.moviedetail

import android.graphics.Color.green
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieappfrontend.R
import com.example.movieappfrontend.data.local.db.AppDatabase
import com.example.movieappfrontend.data.model.Movie
import com.example.movieappfrontend.data.repository.MoviesRepository
import com.example.movieappfrontend.databinding.FragmentHomeBinding
import com.example.movieappfrontend.databinding.FragmentMovieDetailBinding

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"


class MovieDetailFragment : Fragment() {

    private lateinit var viewModel : MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var genres: TextView
    private lateinit var director : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater, R.layout.fragment_movie_detail, container, false
        )
        bindFields(binding)
        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).movieDao()

        viewModelFactory = MovieDetailViewModelFactory(dataSource,application,MovieDetailFragmentArgs.fromBundle(requireArguments()).movie)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.isFavourite.observe(viewLifecycleOwner, Observer { isFavourite ->
            if (isFavourite)
                binding.favouriteButton.setImageResource(android.R.drawable.btn_star_big_on)
            else
                binding.favouriteButton.setImageResource(android.R.drawable.btn_star_big_off)
        })

        viewModel.director.observe(viewLifecycleOwner, Observer { directorText ->
            director.text = directorText
        })

        binding.favouriteButton.setOnClickListener{
            viewModel.clickFavouriteButton()
        }


        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
        populateDetails()



        return binding.root
    }
    private fun bindFields(binding: FragmentMovieDetailBinding){
        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRating
        releaseDate = binding.movieReleaseDate
        overview = binding.movieOverview
        genres = binding.movieGenres
        director = binding.directorText
    }
    private fun populateDetails() {
        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())
        args.movie?.let { movie ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280${movie.backdrop_path}")
                .transform(CenterCrop())
                .into(backdrop)
        }

        args.movie?.let { movie ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text = args.movie.title
        rating.rating = (args.movie.popularity?.toFloat())!! / 2
        releaseDate.text = args.movie.release_date
        overview.text = args.movie.overview
//        genres.text = args.movie.genres.joinToString()
    }

}