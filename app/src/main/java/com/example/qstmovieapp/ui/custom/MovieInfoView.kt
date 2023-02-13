package com.example.qstmovieapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.qstmovieapp.R
import com.example.qstmovieapp.databinding.ViewMovieInfoBinding

class MovieInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var value: String = ""
        set(value) {
            field = value
            binding.txtValue.text = value
        }

    private val binding = ViewMovieInfoBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MovieInfoView)
            binding.txtLabel.text = typedArray.getString(R.styleable.MovieInfoView_mif_label)
            typedArray.recycle()
        }
    }
}