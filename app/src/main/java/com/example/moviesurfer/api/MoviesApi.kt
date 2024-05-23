package com.example.moviesurfer.api

import com.example.moviesurfer.modals.Movie
import com.example.moviesurfer.modals.Series
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

//    @Headers("X-RapidAPI-Key Client-ID ${BuildConfig.API_KEY}")
    @GET("/")
    suspend fun get100Movies(): Response<List<Movie>>

    @GET("/series/")
    suspend fun get100Series(): Response<List<Series>>

    @GET("/top5")
    suspend fun get5Movies(): Response<List<Movie>>
}