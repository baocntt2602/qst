package com.example.qstmovieapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.qstmovieapp.databinding.FragmentMovieDetailBinding
import com.example.qstmovieapp.ui.base.BaseFragment

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        val item = args.movie
        with(binding) {
            imgPoster.setImageDrawable(ContextCompat.getDrawable(requireContext(), item.poster))
            txtDescription.text = item.description
            txtName.text = item.title
            mivGenre.value = item.genre
            mivReleaseDate.value = item.releaseDate
        }
    }
}