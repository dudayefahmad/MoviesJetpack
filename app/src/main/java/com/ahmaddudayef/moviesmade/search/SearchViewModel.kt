package com.ahmaddudayef.moviesmade.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieUseCase
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val queryMovieChanel = BroadcastChannel<String>(Channel.CONFLATED)
    val queryTvShowChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchMovieResult = queryMovieChanel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            movieUseCase.searchMovies(it)
        }
        .asLiveData()

    val searchTvShowResult = queryTvShowChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapConcat {
            tvShowUseCase.searchTvShow(it)
        }
        .asLiveData()

}