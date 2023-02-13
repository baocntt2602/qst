package com.example.qstmovieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qstmovieapp.data.local.SharedPrefManager
import com.example.qstmovieapp.data.model.Movie
import com.example.qstmovieapp.data.model.UIState
import com.example.qstmovieapp.data.repository.MovieRepository
import com.example.qstmovieapp.ui.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Comparator
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    private val _movies: MutableLiveData<UIState<List<Movie>>> = SingleLiveEvent()
    val movies: LiveData<UIState<List<Movie>>> = _movies

    fun getMovies() {
        viewModelScope.launch {
            _movies.postValue(UIState.Loading)
            try {
                val movies = movieRepository.getMovies().apply {
                    forEach { item ->
                        item.isInWatchlist =
                            sharedPrefManager.watchListIds.contains(item.id.toString())
                    }
                }
                _movies.postValue(UIState.Success(movies))
            } catch (e: java.lang.Exception) {
                _movies.postValue(UIState.Error(message = e.message.orEmpty()))
            }
        }
    }

    fun performSorting(comparator: Comparator<Movie>) {
        val sortedList =
            (movies.value as? UIState.Success<List<Movie>>)?.data.orEmpty().toMutableList().apply {
                sortWith(comparator)
            }
        _movies.postValue(UIState.Success(sortedList))
    }
}