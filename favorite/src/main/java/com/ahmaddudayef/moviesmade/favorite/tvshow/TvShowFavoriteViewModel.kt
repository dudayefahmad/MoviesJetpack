package com.ahmaddudayef.moviesmade.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowUseCase

class TvShowFavoriteViewModel(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    fun getFavoriteTvShow() = tvShowUseCase.getAllFavoriteTvShow().asLiveData()
}