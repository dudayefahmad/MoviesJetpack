package com.ahmaddudayef.moviesmade.presentation.detaimovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieFavoriteDataSource
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val movieFavoriteDataSource: MovieFavoriteDataSource
) : ViewModel() {
    val favoriteMovieState = MutableLiveData<State<Unit>>()
    val deleteFavoriteMovieState = MutableLiveData<State<Unit>>()
    val getSingleMovieState = MutableLiveData<State<Movie>>()


    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                movieFavoriteDataSource.insertMovie(movie)
                favoriteMovieState.postValue(State.Success(Unit))
            } catch (e: Exception) {
                favoriteMovieState.postValue(State.Error(e))
            }
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                movieFavoriteDataSource.deleteMovie(movie)
                deleteFavoriteMovieState.postValue(State.Success(Unit))
            } catch (e: Exception) {
                deleteFavoriteMovieState.postValue(State.Error(e))
            }
        }
    }

    fun getSingleMovie(id: Int) {
        viewModelScope.launch {
            try {
                val singleMovie = movieFavoriteDataSource.getSingleMovie(id)
                getSingleMovieState.postValue(State.Success(singleMovie))
            } catch (e: Exception) {
                getSingleMovieState.postValue(State.Error(e))
            }
        }
    }
}