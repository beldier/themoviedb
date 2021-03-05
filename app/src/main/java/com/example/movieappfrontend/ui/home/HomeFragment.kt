package com.example.movieappfrontend.ui.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappfrontend.R
import com.example.movieappfrontend.data.model.themoviedb.Movie
import com.example.movieappfrontend.data.repository.themoviedb.MoviesRepository
import com.example.movieappfrontend.databinding.FragmentHomeBinding


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

    private lateinit var scrollView: ScrollView

    private lateinit var binding: FragmentHomeBinding
    private lateinit var queryString: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        scrollView = binding.scrollView
        popularMovies = binding.popularMovies
        popularMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
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
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
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
        newMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        newMovies.adapter = newMoviesAdapter

        MoviesRepository.getNewMovies(
            onSuccess = ::onNewMoviesFetched,
            onError = ::onError
        )
        setHasOptionsMenu(true)
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)

        val manager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
        searchView.setOnCloseListener {
            Toast.makeText(requireContext(),"backing",Toast.LENGTH_SHORT).show()
            scrollView.isVisible =true
            true
        }

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                Toast.makeText(requireContext(),"Look 1 ",Toast.LENGTH_SHORT).show()
                return true // KEEP IT TO TRUE OR IT DOESN'T OPEN !!
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                showViews()
                return true // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        })
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    queryString = query
                }
                searchView.clearFocus()
                //searchView.setQuery("",false)
                //searchItem.collapseActionView()
                Toast.makeText(requireContext(),"Look $query",Toast.LENGTH_SHORT).show()
                moveToSearchFragment()
                //hideViews()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }
    private fun moveToSearchFragment(){
        this.view?.findNavController()?.navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieSearchFragment(queryString)
        )
    }


    private fun hideViews()
    {
        binding.popularMovies.isGone = true
        binding.upcomingMovies.isGone = true
        binding.newMovies.isGone = true
        binding.popularTitle.isGone = true
        binding.popularDescription.isGone = true
        binding.upcomingTitle.isGone = true
        binding.upcomingDescription.isGone = true
        binding.newMoviesTitle.isGone =true
        binding.newMoviesDescription.isGone = true
    }
    private fun showViews()
    {
        binding.popularMovies.isGone = false
        binding.upcomingMovies.isGone = false
        binding.newMovies.isGone = false
        binding.popularTitle.isGone = false
        binding.popularDescription.isGone = false
        binding.upcomingTitle.isGone = false
        binding.upcomingDescription.isGone = false
        binding.newMoviesTitle.isGone =false
        binding.newMoviesDescription.isGone = false
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