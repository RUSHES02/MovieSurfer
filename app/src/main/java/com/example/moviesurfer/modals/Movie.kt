package com.example.moviesurfer.modals

data class Movie(
    var bigImage: String,
    var description: String,
    var genre: List<String>,
    var id: String,
    var image: String,
    var imdbLink: String,
    var imdbid: String,
    var rank: Int,
    var rating: String,
    var thumbnail: String,
    var title: String,
    var year: Int
)