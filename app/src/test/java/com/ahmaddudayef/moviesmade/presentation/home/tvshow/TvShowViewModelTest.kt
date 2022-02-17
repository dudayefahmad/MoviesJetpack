package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.vo.Resource
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<TvShowViewModel>()

    @Mock
    private lateinit var tvShowRepository: TvShowDataSource

    @Mock
    private lateinit var observerTvShow: Observer<Resource<PagedList<TvShowEntity>>>

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
    fun `success getTvShow`() {
        testDispatcher.runBlockingTest {
            val dummyTvShow = Resource.success(tvShowPagedList)
            Mockito.`when`(dummyTvShow.data?.size).thenReturn(5)
            val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
            tvShow.value = dummyTvShow

            Mockito.`when`(tvShowRepository.getAllTvShow()).thenReturn(tvShow)
            val tvShowEntity = viewModel.getTvShow().value?.data
            Mockito.verify(tvShowRepository).getAllTvShow()
            Assert.assertNotNull(tvShowEntity)
            Assert.assertEquals(5, tvShowEntity?.size)

            viewModel.getTvShow().observeForever(observerTvShow)
            Mockito.verify(observerTvShow).onChanged(dummyTvShow)
        }
    }
}