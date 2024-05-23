package com.example.moviesurfer.ui.fragments.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesurfer.databinding.FragmentMoviesBinding
import com.example.moviesurfer.ui.fragments.movies.MovieAdapter
import com.example.moviesurfer.viewModel.Movie100ViewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val movie100ViewModel : Movie100ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MovieAdapter(requireContext())
        binding.recyclerViewMovies.layoutManager =  GridLayoutManager(requireContext(), 3)
        binding.recyclerViewMovies.adapter = adapter

        movie100ViewModel.movies.observe(viewLifecycleOwner, Observer{ movies ->
            val sortedMovies = movies.sortedBy { it.title }
            Log.d("main view holder", "movies.toString() ")
            adapter.saveData(sortedMovies)
        })

        movie100ViewModel.fetchMovies()
    }
}