package com.example.movieappfrontend.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieappfrontend.R
import com.example.movieappfrontend.databinding.FragmentHomeBinding
import com.example.movieappfrontend.databinding.FragmentMovieDetailBinding

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"


class MovieDetailFragment : Fragment() {
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var genres: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater, R.layout.fragment_movie_detail, container, false
        )
        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRating
        releaseDate = binding.movieReleaseDate
        overview = binding.movieOverview
        genres = binding.movieGenres

        populateDetails()

        return binding.root
    }
    private fun populateDetails() {
        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())
        args.backdropPath?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }

        args.posterPath?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text = args.title
        rating.rating = args.popularity / 2
        releaseDate.text = args.releaseDate
        overview.text = args.overview
        genres.text = args.genres.joinToString()
    }

}