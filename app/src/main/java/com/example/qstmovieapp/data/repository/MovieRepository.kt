package com.example.qstmovieapp.data.repository

import com.example.qstmovieapp.data.model.Movie

interface MovieRepository {
    suspend fun getMovies() : List<Movie>
}