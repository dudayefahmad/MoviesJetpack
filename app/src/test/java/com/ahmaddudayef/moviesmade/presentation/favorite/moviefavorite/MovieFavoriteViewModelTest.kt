package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
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

class MovieFavoriteViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<MovieFavoriteViewModel>()

    @Mock
    private lateinit var movieFavoriteRepository: MovieDataSource

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>


    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(
                module {
                    single {
                        MovieFavoriteViewModel(movieFavoriteRepository)
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
    fun `getFavoriteMovie success`() {
        testDispatcher.runBlockingTest {
            val dummyMovie = moviePagedList
            `when`(dummyMovie.size).thenReturn(5)
            val movie = MutableLiveData<PagedList<MovieEntity>>()
            movie.value = dummyMovie

            `when`(movieFavoriteRepository.getAllFavoriteMovies()).thenReturn(movie)
            val movieEntity = viewModel.getFavoriteMovie().value
            Mockito.verify(movieFavoriteRepository).getAllFavoriteMovies()
            assertNotNull(movieEntity)
            assertEquals(5, movieEntity?.size)

            viewModel.getFavoriteMovie().observeForever(observerMovie)
            Mockito.verify(observerMovie).onChanged(dummyMovie)
        }
    }
}