package com.ahmaddudayef.moviesmade.util

import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow

object DataMapper {

    fun mapMovieToMovieEntity(input: Movie): MovieEntity {
        return MovieEntity(
            input.id,
            input.overview,
            input.title,
            input.posterPath,
            input.backdropPath,
            input.releaseDate,
            input.voteAverage,
            false
        )
    }

    fun mapTvShowToTvShowEntity(input: TvShow): TvShowEntity {
        return TvShowEntity(
            input.id,
            input.firstAirDate,
            input.overview,
            input.posterPath,
            input.backdropPath,
            input.voteAverage,
            input.name,
            false
        )
    }

}