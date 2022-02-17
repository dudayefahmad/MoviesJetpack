package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.vo.Resource

class TvShowViewModel(private val tvShowRepository: TvShowDataSource) : ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return tvShowRepository.getAllTvShow()
    }
}