package com.example.qstmovieapp.ui.home.bottom_sheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qstmovieapp.data.model.Movie
import com.example.qstmovieapp.databinding.FragmentSortBinding
import com.example.qstmovieapp.ui.utils.setSafeOnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortBTSFragment(private val onSort: (Comparator<Movie>) -> Unit) :
    BottomSheetDialogFragment() {

    private var _binding: FragmentSortBinding? = null

    private val binding: FragmentSortBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = FragmentSortBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvent()
    }

    private fun setUpEvent() {
        with(binding) {
            txtByDate.setSafeOnClickListener {
                onSort.invoke(compareBy { it.releaseDate })
                dismiss()
            }

            txtByTitle.setSafeOnClickListener {
                onSort.invoke(compareBy { it.title })
                dismiss()
            }

            txtCancel.setSafeOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}