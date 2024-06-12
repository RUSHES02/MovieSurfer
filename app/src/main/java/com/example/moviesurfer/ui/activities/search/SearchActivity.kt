package com.example.moviesurfer.ui.activities.search

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.ActivitySearchBinding
import com.example.moviesurfer.ui.fragments.movies.MovieAdapter
import com.example.moviesurfer.viewModel.Movie100ViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val movie100ViewModel : Movie100ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SearchAdapter(this)
        binding.recyclerSearch.layoutManager =  LinearLayoutManager(this)
        binding.recyclerSearch.adapter = adapter

        movie100ViewModel.movies.observe(this, Observer{ movies ->
            val sortedMovies = movies.sortedBy { it.title }
            Log.d("main view holder", "movies.toString() ")
            adapter.saveData(sortedMovies)
        })

        movie100ViewModel.fetchMovies()
        
        binding.textSearch.addTextChangedListener {  }
    }
}