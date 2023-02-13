package com.example.qstmovieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.qstmovieapp.data.model.UIState
import com.example.qstmovieapp.databinding.FragmentHomeBinding
import com.example.qstmovieapp.ui.base.BaseFragment
import com.example.qstmovieapp.ui.home.movie_list.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        setUpView()
    }

    private fun setUpView() {
        movieAdapter = MovieAdapter {
            findNavController().navigate(
                HomeFragmentDirections.movieToDetail(it)
            )
        }
        binding.rvMovies.adapter = movieAdapter
    }

    override fun onSubscribeVM() {
        super.onSubscribeVM()
        viewModel.movies.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    movieAdapter.submitList(state.data)
                }
                is UIState.Error -> {}
            }
        }
    }
}