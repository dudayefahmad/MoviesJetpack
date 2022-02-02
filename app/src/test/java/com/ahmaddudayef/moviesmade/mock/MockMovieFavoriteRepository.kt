package com.ahmaddudayef.moviesmade.mock

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieFavoriteDataSource

class MockMovieFavoriteRepository : MovieFavoriteDataSource {
    override suspend fun getMovies(): List<Movie> {
        return LIST_MOVIE
    }

    override suspend fun deleteMovie(movie: Movie) {}

    override suspend fun insertMovie(movie: Movie) {}

    override suspend fun getSingleMovie(id: Int): Movie {
        return MOVIE
    }

    companion object {
        val LIST_MOVIE = arrayListOf(
            Movie(
                1,
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "Rick and Morty",
                false,
                "Rick and Morty",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "2013-12-02",
                8.6,
                248.753,
                false,
                1525
            ),
            Movie(
                2,
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "Rick and Morty",
                false,
                "Rick and Morty",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "2013-12-02",
                8.6,
                248.753,
                false,
                1525
            ),
            Movie(
                3,
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "Rick and Morty",
                false,
                "Rick and Morty",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "2013-12-02",
                8.6,
                248.753,
                false,
                1525
            )
        )

        val MOVIE = Movie(
            1,
            "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
            "en",
            "Rick and Morty",
            false,
            "Rick and Morty",
            "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
            "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
            "2013-12-02",
            8.6,
            248.753,
            false,
            1525
        )
    }
}