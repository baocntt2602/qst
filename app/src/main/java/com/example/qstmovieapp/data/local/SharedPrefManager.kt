package com.example.qstmovieapp.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(
    private val sharedPref: SharedPreferences
) {
    var watchListIds: List<String>
        get() = sharedPref.getString(KEY_WATCHLIST_IDS, null)?.split(",").orEmpty()
        set(value) = sharedPref.edit().putString(KEY_WATCHLIST_IDS, value.joinToString(",")).apply()

    companion object {
        private const val KEY_WATCHLIST_IDS = "key_watchlist_id"
    }
}