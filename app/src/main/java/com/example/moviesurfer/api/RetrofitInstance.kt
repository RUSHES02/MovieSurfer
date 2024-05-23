package com.example.moviesurfer.api

import com.example.moviesurfer.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com"
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: MoviesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

}