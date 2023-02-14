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
import javax.inject.Inject
import kotlin.Comparator

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    private val _movies: MutableLiveData<UIState<List<Movie>>> = SingleLiveEvent()
    val movies: LiveData<UIState<List<Movie>>> = _movies

    private var comparator: Comparator<Movie>? = null

    fun getMovies() {
        viewModelScope.launch {
            _movies.postValue(UIState.Loading)
            try {
                var movies = movieRepository.getMovies().onEach { item ->
                    item.isInWatchlist =
                        sharedPrefManager.watchListIds.contains(item.id.toString())
                }
                comparator?.let {
                    movies = movies.sortedWith(it)
                }
                _movies.postValue(UIState.Success(movies))
            } catch (e: java.lang.Exception) {
                _movies.postValue(UIState.Error(message = e.message.orEmpty()))
            }
        }
    }

    fun performSorting(comparator: Comparator<Movie>) {
        this.comparator = comparator
        val sortedList =
            (movies.value as? UIState.Success<List<Movie>>)?.data.orEmpty().toMutableList().apply {
                sortWith(comparator)
            }
        _movies.postValue(UIState.Success(sortedList))
    }
}