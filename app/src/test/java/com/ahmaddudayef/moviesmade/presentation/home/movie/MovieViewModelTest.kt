package com.ahmaddudayef.moviesmade.presentation.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.repository.MovieRepository
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

class MovieViewModelTest : KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel by inject<MovieViewModel>()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single {
                    MovieViewModel(
                        movieRepository
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
    fun `success getMovies`() {
        testDispatcher.runBlockingTest {
            val dummyMovie = Resource.success(moviePagedList)
            Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
            val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            movie.value = dummyMovie

            Mockito.`when`(movieRepository.getAllMovies()).thenReturn(movie)
            val movieEntity = viewModel.getMovies().value?.data
            Mockito.verify(movieRepository).getAllMovies()
            Assert.assertNotNull(movieEntity)
            Assert.assertEquals(5, movieEntity?.size)

            viewModel.getMovies().observeForever(observerMovie)
            Mockito.verify(observerMovie).onChanged(dummyMovie)
        }
    }
}