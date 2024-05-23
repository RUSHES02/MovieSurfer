package com.example.moviesurfer.modals

data class Series(
    var big_image: String,
    var description: String,
    var genre: List<String>,
    var id: String,
    var image: String,
    var imdb_link: String,
    var imdbid: String,
    var rank: Int,
    var rating: Double,
    var thumbnail: String,
    var title: String,
    var year: String
)
