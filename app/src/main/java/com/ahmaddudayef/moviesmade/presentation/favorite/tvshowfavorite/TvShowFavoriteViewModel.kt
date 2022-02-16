package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.repository.TvShowRepository

class TvShowFavoriteViewModel(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        return tvShowRepository.getAllFavoriteTvShow()
    }
}