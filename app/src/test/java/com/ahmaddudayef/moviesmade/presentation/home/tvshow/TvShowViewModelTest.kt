package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.mock.MockTvShowRepository
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

class TvShowViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<TvShowViewModel>()

    @Mock
    private lateinit var tvShowRepository: TvShowDataSource

    private val mockTvShowRepository = MockTvShowRepository()

    private val testDispatcher = TestCoroutineDispatcher()

    private val language = "id"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    TvShowViewModel(
                        tvShowRepository
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
    fun `getTvShow success`() {
        testDispatcher.runBlockingTest {
            val listTvShow = mockTvShowRepository.getTvShow(language)
            val expected = listOf<State<List<TvShow>>>(
                State.Success(listTvShow)
            )
            BDDMockito.given(tvShowRepository.getTvShow(language)).willReturn(listTvShow)
            viewModel.getTvShow(language)
            val testObserver = viewModel.tvShowState.getTestObserver()
            Assert.assertEquals(testObserver.observedValues, expected)
        }
    }
}