package com.ahmaddudayef.moviesmade.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.SearchDataSource
import kotlinx.coroutines.launch

class SearchViewModel(private val searchDataSource: SearchDataSource) : ViewModel() {

    val searchMovieState = MutableLiveData<State<List<Movie>>>()
    val searchTvShowState = MutableLiveData<State<List<TvShow>>>()

    fun searchMovie(query: String) {
        viewModelScope.launch {
            searchMovieState.postValue(State.Loading())
            try {
                val movies = searchDataSource.searchMovies(query)
                searchMovieState.postValue(State.Success(movies))
            } catch (e: Exception) {
                searchMovieState.postValue(State.Error(e))
            }
        }
    }

    fun searchTvShow(query: String) {
        viewModelScope.launch {
            searchTvShowState.postValue(State.Loading())
            try {
                val tvShow = searchDataSource.searchTvShow(query)
                searchTvShowState.postValue(State.Success(tvShow))
            } catch (e: Exception) {
                searchTvShowState.postValue(State.Error(e))
            }
        }
    }

}