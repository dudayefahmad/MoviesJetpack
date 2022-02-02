package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieFavoriteDataSource
import com.ahmaddudayef.moviesmade.mock.MockMovieFavoriteRepository
import com.ahmaddudayef.moviesmade.util.getTestObserver
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
import org.mockito.MockitoAnnotations

class MovieFavoriteViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<MovieFavoriteViewModel>()

    @Mock
    private lateinit var movieFavoriteRepository: MovieFavoriteDataSource

    private val mockMovieFavoriteRepository = MockMovieFavoriteRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    MovieFavoriteViewModel(movieFavoriteRepository)
                }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `getFavoriteMovie success`() {
        testDispatcher.runBlockingTest {
            val movies = mockMovieFavoriteRepository.getMovies()
            val expected = listOf<State<List<Movie>>>(
                State.Success(movies)
            )
            BDDMockito.given(movieFavoriteRepository.getMovies()).willReturn(movies)
            viewModel.getFavoriteMovie()
            val testObserver = viewModel.movieFavoriteState.getTestObserver()
            assertEquals(testObserver.observedValues, expected)
        }
    }
}