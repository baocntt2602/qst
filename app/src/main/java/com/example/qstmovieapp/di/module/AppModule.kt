package com.example.qstmovieapp.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.qstmovieapp.data.repository.LocalMovieRepoImpl
import com.example.qstmovieapp.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepo(movieRepoImpl: LocalMovieRepoImpl): MovieRepository {
        return movieRepoImpl
    }

    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        )
    }
}