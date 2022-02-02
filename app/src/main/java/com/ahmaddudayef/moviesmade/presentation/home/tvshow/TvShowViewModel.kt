package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.util.EspressoIdlingResource
import kotlinx.coroutines.launch

class TvShowViewModel(private val tvShowDataSource: TvShowDataSource) : ViewModel() {
    val tvShowState = MutableLiveData<State<List<TvShow>>>()

    fun getTvShow(language: String) {
        viewModelScope.launch {
//            EspressoIdlingResource.increment()
            tvShowState.postValue(State.Loading())
            try {
                val tvShow = tvShowDataSource.getTvShow(language)
                tvShowState.postValue(State.Success(tvShow))
//                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                tvShowState.postValue(State.Error(e))
            }
        }
    }
}