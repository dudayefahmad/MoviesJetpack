package com.ahmaddudayef.moviesmade.mock

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.SearchDataSource

class MockSearchRepository : SearchDataSource {
    override suspend fun searchMovies(query: String): List<Movie> {
        return LIST_MOVIE
    }

    override suspend fun searchTvShow(query: String): List<TvShow> {
        return LIST_TVSHOW
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

        val LIST_TVSHOW = arrayListOf(
            TvShow(
                1,
                "2013-12-02",
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Rick and Morty",
                8.0,
                9.0,
                "Rick and Morty",
                8
            ),
            TvShow(
                2,
                "2013-12-02",
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Rick and Morty",
                8.0,
                9.0,
                "Rick and Morty",
                8
            ),
            TvShow(
                3,
                "2013-12-02",
                "Rick adalah orang tua yang tidak seimbang secara mental tetapi berbakat secara ilmiah yang baru-baru ini terhubung kembali dengan keluarganya. Dia menghabiskan sebagian besar waktunya dengan melibatkan cucunya yang muda, Morty, dalam petualangan berbahaya dan aneh di luar angkasa dan alam semesta alternatif. Ditambah dengan kehidupan keluarga Morty yang sudah tidak stabil, peristiwa-peristiwa ini menyebabkan Morty sangat tertekan di rumah dan sekolah.",
                "en",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Rick and Morty",
                8.0,
                9.0,
                "Rick and Morty",
                8
            )
        )
    }
}