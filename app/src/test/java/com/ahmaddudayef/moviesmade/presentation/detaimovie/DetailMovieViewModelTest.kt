package com.ahmaddudayef.moviesmade.presentation.detaimovie

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
import org.junit.*
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailMovieViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<DetailMovieViewModel>()

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
                    DetailMovieViewModel(movieFavoriteRepository)
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
    fun `saveMovie success`() {
        testDispatcher.runBlockingTest {
            val movie = mockMovieFavoriteRepository.getSingleMovie(1)
            val expected = listOf<State<Unit>>(State.Success(Unit))
            BDDMockito.given(movieFavoriteRepository.insertMovie(movie)).willReturn(Unit)
            val actual = viewModel.favoriteMovieState.getTestObserver()
            viewModel.saveMovie(movie)
            BDDMockito.verify(movieFavoriteRepository).insertMovie(movie)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    @Test
    fun `deleteMovie success`() {
        testDispatcher.runBlockingTest {
            val movie = mockMovieFavoriteRepository.getSingleMovie(1)
            val expected = listOf<State<Unit>>(State.Success(Unit))
            BDDMockito.given(movieFavoriteRepository.deleteMovie(movie)).willReturn(Unit)
            val actual = viewModel.deleteFavoriteMovieState.getTestObserver()
            viewModel.deleteMovie(movie)
            BDDMockito.verify(movieFavoriteRepository).deleteMovie(movie)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    @Test
    fun `getSingleMovie success`() {
        testDispatcher.runBlockingTest {
            val movie = mockMovieFavoriteRepository.getSingleMovie(1)
            val id = 1
            val expected = listOf<State<Movie>>(
                State.Success(movie)
            )
            BDDMockito.given(movieFavoriteRepository.getSingleMovie(id)).willReturn(movie)
            val actual = viewModel.getSingleMovieState.getTestObserver()
            viewModel.getSingleMovie(id)
            BDDMockito.verify(movieFavoriteRepository).getSingleMovie(id)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }
}