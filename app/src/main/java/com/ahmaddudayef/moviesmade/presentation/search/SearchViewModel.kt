package com.ahmaddudayef.moviesmade.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.vo.Resource
import kotlinx.coroutines.launch

class SearchViewModel(
    private val movieRepository: MovieDataSource,
    private val tvShowRepository: TvShowDataSource
) : ViewModel() {

    private val _searchMovieState = MutableLiveData<Resource<List<Movie>>>()
    val searchMovieState: LiveData<Resource<List<Movie>>> = _searchMovieState

    private val _searchTvShowState = MutableLiveData<Resource<List<TvShow>>>()
    val searchTvShowState: LiveData<Resource<List<TvShow>>> = _searchTvShowState

    fun searchMovie(query: String) {
        viewModelScope.launch {
            _searchMovieState.postValue(Resource.loading(null))
            try {
                val searchMovies = movieRepository.searchMovies(query)
                _searchMovieState.postValue(Resource.success(searchMovies))
            } catch (e: Exception) {
                _searchMovieState.postValue(Resource.error(e.message, null))
            }
        }
    }

    fun searchTvShow(query: String) {
        viewModelScope.launch {
            _searchTvShowState.postValue(Resource.loading(null))
            try {
                val tvShow = tvShowRepository.searchTvShow(query)
                _searchTvShowState.postValue(Resource.success(tvShow))
            } catch (e: Exception) {
                _searchTvShowState.postValue(Resource.error(e.message, null))
            }
        }
    }

}