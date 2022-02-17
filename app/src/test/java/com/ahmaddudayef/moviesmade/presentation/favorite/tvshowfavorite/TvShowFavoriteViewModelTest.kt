package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TvShowFavoriteViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<TvShowFavoriteViewModel>()

    @Mock
    private lateinit var tvShowFavoriteRepository: TvShowDataSource

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

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
            val dummyTvShow = tvShowPagedList
            `when`(dummyTvShow.size).thenReturn(5)
            val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
            tvShow.value = dummyTvShow

            `when`(tvShowFavoriteRepository.getAllFavoriteTvShow()).thenReturn(tvShow)
            val tvShowEntity = viewModel.getFavoriteTvShow().value
            Mockito.verify(tvShowFavoriteRepository).getAllFavoriteTvShow()
            assertNotNull(tvShowEntity)
            assertEquals(5, tvShowEntity?.size)

            viewModel.getFavoriteTvShow().observeForever(observerTvShow)
            Mockito.verify(observerTvShow).onChanged(dummyTvShow)
        }
    }
}