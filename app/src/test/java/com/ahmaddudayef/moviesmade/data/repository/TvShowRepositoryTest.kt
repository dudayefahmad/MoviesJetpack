package com.ahmaddudayef.moviesmade.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ahmaddudayef.moviesmade.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.util.AppExecutors
import com.ahmaddudayef.moviesmade.util.DataDummy
import com.ahmaddudayef.moviesmade.util.LiveDataTestUtil
import com.ahmaddudayef.moviesmade.util.PagedListUtil
import com.ahmaddudayef.moviesmade.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
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
import org.mockito.MockitoAnnotations

class TvShowRepositoryTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val tvShowRepository by inject<TvShowRepository>()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    private val tvShowResponse = DataDummy.generateDataTvShowDummy()
    private val tvShow = tvShowResponse[0]
    private val tvShowId = tvShowResponse[0].id

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(module {
                single {
                    TvShowRepository(
                        localDataSource,
                        remoteDataSource,
                        appExecutors
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
    fun `success get all tv show`() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(localDataSource.getAllTvShows()).thenReturn(dataSourceFactory)
        tvShowRepository.getAllTvShow()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        Mockito.verify(localDataSource).getAllTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun `success get all favorite tv show`() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(localDataSource.getAllFavoriteTvShows()).thenReturn(dataSourceFactory)
        tvShowRepository.getAllFavoriteTvShow()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMoviesDummy()))
        Mockito.verify(localDataSource).getAllFavoriteTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun `success get detail tv show by id`() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        val tvShowId = tvShowId
        Mockito.`when`(localDataSource.getDetailTvShowById(tvShowId)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtil.getValue(tvShowRepository.getDetailTvShowById(tvShow.id))
        Mockito.verify(localDataSource).getDetailTvShowById(tvShow.id)
        assertNotNull(data)
        assertEquals(tvShow.id, data.id)
    }
}