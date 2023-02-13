package com.example.qstmovieapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.qstmovieapp.data.local.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    fun isInWatchList(id: Int): Boolean {
        return sharedPrefManager.watchListIds.contains(id.toString())
    }

    fun addToWatchlist(id: Int) {
        val newList = sharedPrefManager.watchListIds.toMutableList().apply {
            add(id.toString())
        }
        sharedPrefManager.watchListIds = newList
    }

    fun removeFromWatchlist(id: Int) {
        val newList = sharedPrefManager.watchListIds.toMutableList().apply {
            remove(id.toString())
        }
        sharedPrefManager.watchListIds = newList
    }
}