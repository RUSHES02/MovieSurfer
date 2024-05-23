package com.example.moviesurfer.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.ActivityMainBinding
import com.example.moviesurfer.ui.activities.search.SearchActivity
import com.example.moviesurfer.ui.fragments.movies.MoviesFragment
import com.example.moviesurfer.ui.fragments.series.SeriesFragment
import com.example.moviesurfer.viewModel.Movie100ViewModel
import com.example.moviesurfer.viewModel.Movie5ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movie100ViewModel : Movie100ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coroselAdapter = CoroselAdapter(this)
        binding.viewPagerCorosel.adapter = coroselAdapter

        movie100ViewModel.movies.observe(this, Observer{ movies ->
            Log.d("main view holder", "movies.toString() ")
            val sortedMovies = movies.sortedBy { it.rank }.take(5)
            coroselAdapter.saveData(sortedMovies)
        })

        movie100ViewModel.fetchMovies()


        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                binding.viewPagerCorosel.setCurrentItem((binding.viewPagerCorosel.currentItem + 1) % coroselAdapter.itemCount, true)
                handler.postDelayed(this, 3000) // Change view every second
            }
        }, 5000)

        binding.mainAppBar.textPageTittle.text = "Home"

        binding.buttonMovies.setOnClickListener{
            binding.buttonMovies.setBackgroundResource(R.drawable.bg_button_active)
            binding.buttonSeries.setBackgroundResource(R.drawable.bg_button_inactive)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, MoviesFragment())
            transaction.addToBackStack(null) // Optional: Add the transaction to the back stack
            transaction.commit()

        }

        binding.buttonSeries.setOnClickListener{
            binding.buttonSeries.setBackgroundResource(R.drawable.bg_button_active)
            binding.buttonMovies.setBackgroundResource(R.drawable.bg_button_inactive)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, SeriesFragment())
            transaction.addToBackStack(null) // Optional: Add the transaction to the back stack
            transaction.commit()
        }

        binding.mainAppBar.icSearch.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}