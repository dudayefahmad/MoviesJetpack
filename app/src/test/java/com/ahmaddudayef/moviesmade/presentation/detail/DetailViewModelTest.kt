package com.ahmaddudayef.moviesmade.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.util.DataDummy
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailViewModelTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val viewModel by inject<DetailViewModel>()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var movieRepository: MovieDataSource

    @Mock
    private lateinit var tvShowRepository: TvShowDataSource

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    private val dummyMovie = DataDummy.generateDataMoviesDummy()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
    private val tvShowId = dummyTvShow.id


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(
                module {
                    single {
                        DetailViewModel(movieRepository, tvShowRepository)
                    }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `success getDetailMovieById`() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        `when`(movieRepository.getDetailMovieById(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getDetailMovieById(movieId).value

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData?.id)
        assertEquals(dummyMovie.title, movieData?.title)
        assertEquals(dummyMovie.overview, movieData?.overview)
        assertEquals(dummyMovie.posterPath, movieData?.posterPath)
        assertEquals(dummyMovie.backdropPath, movieData?.backdropPath)

        viewModel.getDetailMovieById(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }


    @Test
    fun `success getDetailTvShowById`() {
        val tvShowDummy = MutableLiveData<TvShowEntity>()
        tvShowDummy.value = dummyTvShow

        `when`(tvShowRepository.getDetailTvShowById(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getDetailTvShowById(tvShowId).value

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData?.id)
        assertEquals(dummyTvShow.name, tvShowData?.name)
        assertEquals(dummyTvShow.overview, tvShowData?.overview)
        assertEquals(dummyTvShow.posterPath, tvShowData?.posterPath)
        assertEquals(dummyTvShow.backdropPath, tvShowData?.backdropPath)

        viewModel.getDetailTvShowById(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}