package com.ahmaddudayef.moviesmade.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShows
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
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

class TvShowRepositoryTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val repository by inject<TvShowDataSource>()

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(module { single { TvShowRepository(apiService) as TvShowDataSource } })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `success get tvShow list`() {
        testDispatcher.runBlockingTest {
            val language = "en"
            val tvShow = TVSHOW
            val expected = TVSHOW.results

            BDDMockito.given(apiService.getTvShow(BuildConfig.API_KEY, language)).willReturn(tvShow)
            val actual = repository.getTvShow(language)

            Mockito.verify(apiService).getTvShow(BuildConfig.API_KEY, language)
            assertEquals(expected, actual)
        }
    }

    companion object {
        val TVSHOW = TvShows(
            1,
            10,
            MockTvShowRepository.LIST_TVSHOW,
            10
        )
    }
}