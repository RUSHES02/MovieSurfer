package com.example.moviesurfer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesurfer.api.RetrofitInstance
import com.example.moviesurfer.modals.Movie
import com.example.moviesurfer.modals.Series
import kotlinx.coroutines.launch

class Series100ViewModel: ViewModel() {
    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> get() = _series

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchSeries() {
        Log.d("view model", "here")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.get100Series()
                if (response.isSuccessful && response.body() != null) {
                    _series.postValue(response.body())
                } else {
                    Log.e("view model", "${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("view model 2", "${e.message}")
            }
        }
    }
}