package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowFavoriteDataSource
import com.ahmaddudayef.moviesmade.mock.MockTvShowFavoriteRepository
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

class TvShowFavoriteViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<TvShowFavoriteViewModel>()

    @Mock
    private lateinit var tvShowFavoriteRepository: TvShowFavoriteDataSource

    private val mockTvShowFavoriteRepository = MockTvShowFavoriteRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    TvShowFavoriteViewModel(tvShowFavoriteRepository)
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
    fun `getFavoriteTvShow success`() {
        testDispatcher.runBlockingTest {
            val listTvShow = mockTvShowFavoriteRepository.getTvShow()
            val expected = listOf<State<List<TvShow>>>(
                State.Success(listTvShow)
            )
            BDDMockito.given(tvShowFavoriteRepository.getTvShow()).willReturn(listTvShow)
            viewModel.getFavoriteTvShow()
            val testObserver = viewModel.tvShowFavoriteState.getTestObserver()
            assertEquals(testObserver.observedValues, expected)
        }
    }
}