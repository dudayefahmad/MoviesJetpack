package com.ahmaddudayef.moviesmade.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movies
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShows
import com.ahmaddudayef.moviesmade.data.source.SearchDataSource
import com.ahmaddudayef.moviesmade.mock.MockMovieRepository
import com.ahmaddudayef.moviesmade.mock.MockTvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchRepositoryTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val repository by inject<SearchDataSource>()

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(module { single { SearchRepository(apiService) as SearchDataSource } })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `success search movies`() {
        testDispatcher.runBlockingTest {
            val query = "rick"
            val movies = MOVIES
            val expected = MOVIES.results

            BDDMockito.given(apiService.searchMovie(BuildConfig.API_KEY, query)).willReturn(movies)
            val actual = repository.searchMovies(query)

            Mockito.verify(apiService).searchMovie(BuildConfig.API_KEY, query)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `success search tvshow`() {
        testDispatcher.runBlockingTest {
            val query = "rick"
            val tvShow = TVSHOW
            val expected = TVSHOW.results

            BDDMockito.given(apiService.searchTVShow(BuildConfig.API_KEY, query)).willReturn(tvShow)
            val actual = repository.searchTvShow(query)

            Mockito.verify(apiService).searchTVShow(BuildConfig.API_KEY, query)
            assertEquals(expected, actual)
        }
    }

    companion object {
        val MOVIES = Movies(
            1,
            10,
            MockMovieRepository.LIST_MOVIE,
            10
        )

        val TVSHOW = TvShows(
            1,
            10,
            MockTvShowRepository.LIST_TVSHOW,
            10
        )
    }
}