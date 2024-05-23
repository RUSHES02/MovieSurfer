package com.example.moviesurfer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesurfer.api.RetrofitInstance
import com.example.moviesurfer.modals.Movie
import kotlinx.coroutines.launch

class Movie5ViewModel: ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies: LiveData<List<Movie>> get() = _movies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetch5Movies() {
        Log.d("view model", "here")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.get5Movies()
                if (response.isSuccessful && response.body() != null) {
                    _movies.postValue(response.body())
                } else {
                    Log.e("view model", "${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("view model 2", "${e.message}")
            }
        }
    }
}