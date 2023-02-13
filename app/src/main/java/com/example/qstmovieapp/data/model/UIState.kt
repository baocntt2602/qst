package com.example.qstmovieapp.data.model

sealed class UIState<out R> {

    data class Success<out T>(val data: T) : UIState<T>()

    data class Error(val code: String? = null, val message: String) : UIState<Nothing>()

    object Loading : UIState<Nothing>()
}