package com.example.qstmovieapp.di.module

import com.example.qstmovieapp.data.repository.LocalMovieRepoImpl
import com.example.qstmovieapp.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepo(movieRepoImpl: LocalMovieRepoImpl): MovieRepository
}