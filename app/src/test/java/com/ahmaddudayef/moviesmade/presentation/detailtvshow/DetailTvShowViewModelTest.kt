package com.ahmaddudayef.moviesmade.presentation.detailtvshow

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

class DetailTvShowViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<DetailTvShowViewModel>()

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
                    DetailTvShowViewModel(tvShowFavoriteRepository)
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
    fun `saveTvShow success`() {
        testDispatcher.runBlockingTest {
            val tvShow = mockTvShowFavoriteRepository.getSingleTvShow(1)
            val expected = listOf<State<Unit>>(State.Success(Unit))
            BDDMockito.given(tvShowFavoriteRepository.insertTvShow(tvShow)).willReturn(Unit)
            val actual = viewModel.favoriteTvShowState.getTestObserver()
            viewModel.saveTvShow(tvShow)
            BDDMockito.verify(tvShowFavoriteRepository).insertTvShow(tvShow)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    @Test
    fun `deleteTvShow success`() {
        testDispatcher.runBlockingTest {
            val tvShow = mockTvShowFavoriteRepository.getSingleTvShow(1)
            val expected = listOf<State<Unit>>(State.Success(Unit))
            BDDMockito.given(tvShowFavoriteRepository.deleteTvShow(tvShow)).willReturn(Unit)
            val actual = viewModel.deleteFavoriteTvShowState.getTestObserver()
            viewModel.deleteTvShow(tvShow)
            BDDMockito.verify(tvShowFavoriteRepository).deleteTvShow(tvShow)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    @Test
    fun `getSingleTvShow success`() {
        testDispatcher.runBlockingTest {
            val tvShow = mockTvShowFavoriteRepository.getSingleTvShow(1)
            val id = 1
            val expected = listOf<State<TvShow>>(
                State.Success(tvShow)
            )
            BDDMockito.given(tvShowFavoriteRepository.getSingleTvShow(id)).willReturn(tvShow)
            val actual = viewModel.getSingleTvShowState.getTestObserver()
            viewModel.getSingleLocalTvShow(id)
            BDDMockito.verify(tvShowFavoriteRepository).getSingleTvShow(id)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }
}