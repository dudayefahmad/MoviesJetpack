package com.ahmaddudayef.moviesmade.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    fun setFavoriteMovie(movie: MovieEntity)

    suspend fun searchMovies(query: String): List<Movie>

    fun getDetailMovie(id: Int): LiveData<ApiResponse<Movie>>
}