package com.ahmaddudayef.moviesmade.presentation.home.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.util.EspressoIdlingResource
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataSource: MovieDataSource) : ViewModel() {
    val movieState = MutableLiveData<State<List<Movie>>>()

    fun getMovies(language: String) {
        viewModelScope.launch {
//            EspressoIdlingResource.increment()
            movieState.postValue(State.Loading())
            try {
                val movies = movieDataSource.getMovies(language)
                movieState.postValue(State.Success(movies))
//                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                movieState.postValue(State.Error(e))
            }
        }
    }
}