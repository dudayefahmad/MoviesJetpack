package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.repository.TvShowRepository
import com.ahmaddudayef.moviesmade.vo.Resource

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return tvShowRepository.getAllTvShow()
    }
}