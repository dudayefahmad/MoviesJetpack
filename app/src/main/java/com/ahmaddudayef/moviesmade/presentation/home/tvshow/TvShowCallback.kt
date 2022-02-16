package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(data: TvShowEntity)
}