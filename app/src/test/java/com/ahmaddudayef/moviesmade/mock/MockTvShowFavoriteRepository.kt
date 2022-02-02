package com.ahmaddudayef.moviesmade.mock

import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowFavoriteDataSource

class MockTvShowFavoriteRepository : TvShowFavoriteDataSource {

    override suspend fun getTvShow(): List<TvShow> {
        return LIST_TVSHOW
    }

    override suspend fun deleteTvShow(tvShow: TvShow) {}

    override suspend fun insertTvShow(tvShow: TvShow) {}

    override suspend fun getSingleTvShow(id: Int): TvShow {
        return TV_SHOW
    }

    companion object {
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
        val TV_SHOW = TvShow(
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
        )
    }
}