package com.ahmaddudayef.moviesmade.presentation.detailtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowFavoriteDataSource
import kotlinx.coroutines.launch

class DetailTvShowViewModel(
    private val tvShowFavoriteDataSource: TvShowFavoriteDataSource
) : ViewModel() {

    val favoriteTvShowState = MutableLiveData<State<Unit>>()
    val deleteFavoriteTvShowState = MutableLiveData<State<Unit>>()
    val getSingleTvShowState = MutableLiveData<State<TvShow>>()

    fun saveTvShow(tvShow: TvShow) {
        viewModelScope.launch {
            try {
                tvShowFavoriteDataSource.insertTvShow(tvShow)
                favoriteTvShowState.postValue(State.Success(Unit))
            } catch (e: Exception) {
                favoriteTvShowState.postValue(State.Error(e))
            }
        }
    }

    fun deleteTvShow(tvShow: TvShow) {
        viewModelScope.launch {
            try {
                tvShowFavoriteDataSource.deleteTvShow(tvShow)
                deleteFavoriteTvShowState.postValue(State.Success(Unit))
            } catch (e: Exception) {
                deleteFavoriteTvShowState.postValue(State.Error(e))
            }
        }
    }

    fun getSingleLocalTvShow(id: Int) {
        viewModelScope.launch {
            try {
                val singleTvShow = tvShowFavoriteDataSource.getSingleTvShow(id)
                getSingleTvShowState.postValue(State.Success(singleTvShow))
            } catch (e: Exception) {
                deleteFavoriteTvShowState.postValue(State.Error(e))
            }
        }
    }
}