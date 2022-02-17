package com.ahmaddudayef.moviesmade.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.remote.api.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RemoteDataSource(private val apiService: ApiService) {

    private val api_key = BuildConfig.API_KEY

    fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMoviesResponse = MutableLiveData<ApiResponse<List<Movie>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getMovies(api_key)
                if (response.results.isNotEmpty()) {
                    resultMoviesResponse.postValue(ApiResponse.success(response.results))
                } else {
                    resultMoviesResponse.postValue(ApiResponse.empty())
                }
            } catch (e: Exception) {
                resultMoviesResponse.postValue(
                    ApiResponse.error(e.toString())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMoviesResponse
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShowsResponse = MutableLiveData<ApiResponse<List<TvShow>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getTvShows(api_key)
                if (response.results.isNotEmpty()) {
                    resultTvShowsResponse.postValue(ApiResponse.success(response.results))
                } else {
                    resultTvShowsResponse.postValue(ApiResponse.empty())
                }
            } catch (e: Exception) {
                resultTvShowsResponse.postValue(
                    ApiResponse.error(e.toString())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowsResponse
    }

    suspend fun searchMovies(query: String): List<Movie> {
        val response = apiService.searchMovies(api_key, query)
        return response.results
    }

    suspend fun searchTvShows(query: String): List<TvShow> {
        val response = apiService.searchTvShows(api_key, query)
        return response.results
    }

    fun getDetailMovie(id: Int): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<Movie>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getDetailMovie(id, api_key)
                if (response != null) {
                    resultDetailMovie.postValue(ApiResponse.success(response))
                } else {
                    resultDetailMovie.postValue(ApiResponse.empty())
                }
            } catch (e: Exception) {
                resultDetailMovie.postValue(
                    ApiResponse.error(e.toString())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultDetailMovie
    }

    fun getDetailTvShow(id: Int): LiveData<ApiResponse<TvShow>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<TvShow>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getDetailTvShow(id, api_key)
                if (response != null) {
                    resultDetailTvShow.postValue(ApiResponse.success(response))
                } else {
                    resultDetailTvShow.postValue(ApiResponse.empty())
                }
            } catch (e: Exception) {
                resultDetailTvShow.postValue(
                    ApiResponse.error(e.toString())
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultDetailTvShow
    }


}