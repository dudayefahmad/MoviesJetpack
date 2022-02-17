package com.ahmaddudayef.moviesmade.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ahmaddudayef.moviesmade.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
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
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieRepositoryTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val movieRepository by inject<MovieRepository>()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    private val moviesResponse = DataDummy.generateDataMoviesDummy()
    private val movie = moviesResponse[0]
    private val movieId = moviesResponse[0].id

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(module {
                single {
                    MovieRepository(localDataSource, remoteDataSource, appExecutors)
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
    fun `success get all movies`() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMoviesDummy()))
        verify(localDataSource).getAllMovies()
        assertNotNull(movieEntity.data)
        assertEquals(moviesResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun `success get all favorite movies`() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllFavoriteMovies()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMoviesDummy()))
        verify(localDataSource).getAllFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(moviesResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun `success get detail movie by id`() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        val movieId = movieId
        `when`(localDataSource.getDetailMovieById(movieId)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(movieRepository.getDetailMovieById(movie.id))
        verify(localDataSource).getDetailMovieById(movie.id)
        assertNotNull(data)
        assertEquals(movie.id, data.id)
    }
}