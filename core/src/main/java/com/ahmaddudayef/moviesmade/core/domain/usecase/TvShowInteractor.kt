package com.ahmaddudayef.moviesmade.core.domain.usecase

import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow

class TvShowInteractor(private val iTvShowRepository: ITvShowRepository) : TvShowUseCase {
    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
        iTvShowRepository.getAllTvShow()

    override fun getAllFavoriteTvShow(): Flow<List<TvShow>> =
        iTvShowRepository.getAllFavoriteTvShow()

    override fun getDetailTvShowById(id: Int): Flow<TvShow> =
        iTvShowRepository.getDetailTvShowById(id)

    override fun setFavoriteTvShow(tvShow: TvShow) =
        iTvShowRepository.setFavoriteTvShow(tvShow)

    override suspend fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> =
        iTvShowRepository.searchTvShow(query)

    override fun getDetailTvShowByIdFromNetwork(id: Int): Flow<Resource<TvShow>> =
        iTvShowRepository.getDetailTvShowByIdFromNetwork(id)
}