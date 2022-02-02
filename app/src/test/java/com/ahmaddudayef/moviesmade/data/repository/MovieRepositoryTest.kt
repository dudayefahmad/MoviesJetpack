package com.ahmaddudayef.moviesmade.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movies
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.mock.MockMovieRepository
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
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val repository by inject<MovieDataSource>()

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(module { single { MovieRepository(apiService) as MovieDataSource } })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `success get movies list`() {
        testDispatcher.runBlockingTest {
            val language = "en"
            val movies = MOVIES
            val expected = MOVIES.results

            given(apiService.getMovies(BuildConfig.API_KEY, language)).willReturn(movies)
            val actual = repository.getMovies(language)

            Mockito.verify(apiService).getMovies(BuildConfig.API_KEY, language)
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
    }
}