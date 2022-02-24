package com.ahmaddudayef.moviesmade.home.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowUseCase

class TvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {

    fun getTvShow() = tvShowUseCase.getAllTvShow().asLiveData()
}