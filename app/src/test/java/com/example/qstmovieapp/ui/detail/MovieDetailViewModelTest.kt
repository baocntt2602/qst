package com.example.qstmovieapp.ui.detail

import com.example.qstmovieapp.data.local.SharedPrefManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val sharedPrefManager: SharedPrefManager = mockk()

    @Before
    fun setUp() {
        val mockWatchlist = listOf("1", "2")
        every { sharedPrefManager.watchListIds } returns mockWatchlist
        viewModel = MovieDetailViewModel(sharedPrefManager)
    }

    @Test
    fun `check if a movie is in watchlist`() {
        assertEquals(true, viewModel.isInWatchList(2))
        assertEquals(false, viewModel.isInWatchList(3))
    }

    @Test
    fun `verify add to watchlist function`() {
        val slot = slot<List<String>>()
        every { sharedPrefManager.watchListIds = capture(slot) } returns Unit
        viewModel.addToWatchlist(3)
        verify {
            sharedPrefManager.watchListIds = slot.captured
        }
        assertEquals(3, slot.captured.size)
    }

    @Test
    fun `verify remove from watchlist function`() {
        val slot = slot<List<String>>()
        every { sharedPrefManager.watchListIds = capture(slot) } returns Unit
        viewModel.removeFromWatchlist(1)
        verify {
            sharedPrefManager.watchListIds = slot.captured
        }
        assertEquals(slot.captured.size, 1)
    }
}