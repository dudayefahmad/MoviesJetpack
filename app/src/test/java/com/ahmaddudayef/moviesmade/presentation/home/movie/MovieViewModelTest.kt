package com.ahmaddudayef.moviesmade.presentation.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.mock.MockMovieRepository
import com.ahmaddudayef.moviesmade.util.getTestObserver
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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

class MovieViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<MovieViewModel>()

    @Mock
    private lateinit var movieRepository: MovieDataSource

    private val mockMovieRepository = MockMovieRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    private val language = "id"


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    MovieViewModel(
                        movieRepository
                    )
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
    fun `getMovies success`() {
        testDispatcher.runBlockingTest {
            val listMovie = mockMovieRepository.getMovies(language)
            val expected = listOf<State<List<Movie>>>(
                State.Success(listMovie)
            )
            BDDMockito.given(movieRepository.getMovies(language)).willReturn(listMovie)
            viewModel.getMovies(language)
            val testObserver = viewModel.movieState.getTestObserver()
            Assert.assertEquals(testObserver.observedValues, expected)
        }
    }
}