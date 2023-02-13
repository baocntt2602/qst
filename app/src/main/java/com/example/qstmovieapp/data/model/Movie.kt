package com.example.qstmovieapp.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val duration: String,
    val genre: String,
    val releaseDate: String,
    val trailerLink: String,
    @DrawableRes val poster: Int
): Parcelable