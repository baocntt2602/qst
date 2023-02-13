package com.example.qstmovieapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.qstmovieapp.data.model.Movie
import com.example.qstmovieapp.databinding.FragmentMovieDetailBinding
import com.example.qstmovieapp.ui.base.BaseFragment
import com.example.qstmovieapp.ui.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private val args by navArgs<MovieDetailFragmentArgs>()
    private val viewModel: MovieDetailViewModel by viewModels()
    private val movie: Movie
        get() = args.movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpEvent()
        setUpBtnVisibility()
    }

    private fun setUpEvent() {
        with(binding) {
            btnAdd.setSafeOnClickListener {
                viewModel.addToWatchlist(movie.id)
                setUpBtnVisibility()
            }

            btnRemove.setSafeOnClickListener {
                viewModel.removeFromWatchlist(movie.id)
                setUpBtnVisibility()
            }
        }
    }

    private fun setUpView() {
        with(binding) {
            imgPoster.setImageDrawable(ContextCompat.getDrawable(requireContext(), movie.poster))
            txtDescription.text = movie.description
            txtName.text = movie.title
            mivGenre.value = movie.genre
            mivReleaseDate.value = movie.releaseDate
        }
    }

    private fun setUpBtnVisibility() {
        binding.btnAdd.isVisible = !viewModel.isInWatchList(movie.id)
        binding.btnRemove.isVisible = viewModel.isInWatchList(movie.id)
    }
}