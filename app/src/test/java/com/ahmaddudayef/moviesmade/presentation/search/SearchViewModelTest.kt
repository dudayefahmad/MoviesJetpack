package com.ahmaddudayef.moviesmade.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.SearchDataSource
import com.ahmaddudayef.moviesmade.mock.MockSearchRepository
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

class SearchViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<SearchViewModel>()

    @Mock
    private lateinit var searchRepository: SearchDataSource

    private val mockSearchRepository = MockSearchRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    SearchViewModel(searchRepository)
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
    fun `search movie success`() {
        testDispatcher.runBlockingTest {
            val title = "Rick"
            val listMovie = mockSearchRepository.searchMovies(title)
            val expected = listOf<State<List<Movie>>>(
                State.Success(listMovie)
            )
            BDDMockito.given(searchRepository.searchMovies(title)).willReturn(listMovie)
            viewModel.searchMovie(title)
            val testObserver = viewModel.searchMovieState.getTestObserver()
            assertEquals(testObserver.observedValues, expected)
        }
    }

    @Test
    fun `search tvshow success`() {
        testDispatcher.runBlockingTest {
            val title = "Rick"
            val listTvShow = mockSearchRepository.searchTvShow(title)
            val expected = listOf<State<List<TvShow>>>(
                State.Success(listTvShow)
            )
            BDDMockito.given(searchRepository.searchTvShow(title)).willReturn(listTvShow)
            viewModel.searchTvShow(title)
            val testObserver = viewModel.searchTvShowState.getTestObserver()
            assertEquals(testObserver.observedValues, expected)
        }
    }


}