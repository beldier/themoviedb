package com.example.movieappfrontend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.movieappfrontend.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        binding.buttonDetail.setOnClickListener { view:View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}