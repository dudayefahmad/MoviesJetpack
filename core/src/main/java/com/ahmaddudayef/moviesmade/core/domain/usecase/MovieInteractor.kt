package com.ahmaddudayef.moviesmade.core.domain.usecase

import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        iMovieRepository.getAllMovies()

    override fun getAllFavoriteMovies(): Flow<List<Movie>> =
        iMovieRepository.getAllFavoriteMovies()

    override fun getDetailMovieById(id: Int): Flow<Movie> =
        iMovieRepository.getDetailMovieById(id)

    override fun setFavoriteMovie(movie: Movie) =
        iMovieRepository.setFavoriteMovie(movie)

    override suspend fun searchMovies(query: String): Flow<Resource<List<Movie>>> =
        iMovieRepository.searchMovies(query)

    override fun getDetailMovieByIdFromNetwork(id: Int): Flow<Resource<Movie>> =
        iMovieRepository.getDetailMovieByIdFromNetwork(id)
}