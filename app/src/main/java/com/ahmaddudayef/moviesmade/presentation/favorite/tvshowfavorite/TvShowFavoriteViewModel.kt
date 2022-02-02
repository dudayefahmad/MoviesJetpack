package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowFavoriteDataSource
import kotlinx.coroutines.launch

class TvShowFavoriteViewModel(
    private val tvShowFavoriteDataSource: TvShowFavoriteDataSource
) : ViewModel() {

    val tvShowFavoriteState = MutableLiveData<State<List<TvShow>>>()

    fun getFavoriteTvShow() {
        viewModelScope.launch {
            try {
                val tvShow = tvShowFavoriteDataSource.getTvShow()
                tvShowFavoriteState.postValue(State.Success(tvShow))
            } catch (e: Exception) {
                tvShowFavoriteState.postValue(State.Error(e))
            }
        }
    }
}