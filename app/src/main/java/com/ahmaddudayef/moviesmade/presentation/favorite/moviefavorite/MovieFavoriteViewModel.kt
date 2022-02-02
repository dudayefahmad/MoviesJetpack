package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieFavoriteDataSource
import com.ahmaddudayef.moviesmade.util.EspressoIdlingResource
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(
    private val movieFavoriteDataSource: MovieFavoriteDataSource
) : ViewModel() {

    val movieFavoriteState = MutableLiveData<State<List<Movie>>>()

    fun getFavoriteMovie() {
//        EspressoIdlingResource.increment()
        viewModelScope.launch {
            try {
                val movies = movieFavoriteDataSource.getMovies()
                movieFavoriteState.postValue(State.Success(movies))
//                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                movieFavoriteState.postValue(State.Error(e))
            }
        }
    }
}