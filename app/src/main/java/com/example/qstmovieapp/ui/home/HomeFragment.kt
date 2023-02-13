package com.example.qstmovieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.qstmovieapp.data.model.UIState
import com.example.qstmovieapp.databinding.FragmentHomeBinding
import com.example.qstmovieapp.ui.base.BaseFragment
import com.example.qstmovieapp.ui.home.bottom_sheet.SortBTSFragment
import com.example.qstmovieapp.ui.home.movie_list.MovieAdapter
import com.example.qstmovieapp.ui.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        setUpView()
        setUpEvent()
    }

    private fun setUpEvent() {
        binding.txtSort.setSafeOnClickListener {
            SortBTSFragment(
                onSort = { comparator ->
                    viewModel.performSorting(comparator)
                }
            ).show(childFragmentManager, "")
        }
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