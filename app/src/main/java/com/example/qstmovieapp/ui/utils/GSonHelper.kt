package com.example.qstmovieapp.ui.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonHelper {

    val gson = Gson()

    fun <T> T.toJson(): String {
        return gson.toJson(this)
    }

    inline fun <reified T> String.toObject(): T {
        return gson.fromJson(this, object: TypeToken<T>() {}.type)
    }
}