package com.example.qstmovieapp.ui.home.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.qstmovieapp.data.model.Movie
import com.example.qstmovieapp.databinding.ItemMovieBinding

class MovieViewHolder(
    private val binding: ItemMovieBinding, private val onClicked: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, onClicked: (Movie) -> Unit): MovieViewHolder {
            val binding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return MovieViewHolder(binding, onClicked)
        }
    }

    fun onBind(item: Movie) {
        with(binding) {
            txtName.text = item.title
            txtInfo.text = item.duration + " - " + item.genre
            imgPoster.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.poster))
            root.setOnClickListener {
                onClicked.invoke(item)
            }
        }
    }
}