package com.ahmaddudayef.moviesmade.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.data.Type.TYPE_MOVIE
import com.ahmaddudayef.moviesmade.core.data.Type.TYPE_TVSHOW
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.util.loadImageUrl
import com.ahmaddudayef.moviesmade.core.util.showSnackbar
import com.ahmaddudayef.moviesmade.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()
    private var isFavorite = false
    private var menuItem: Menu? = null
    private var type: String? = null
    private var isFromSearch: Boolean = false
    private var movieCopy: Movie? = null
    private var tvShowCopy: TvShow? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(
            EXTRA_DATA,
            0
        )
        type =
            intent.getStringExtra(EXTRA_TYPE)
        isFromSearch = intent.getBooleanExtra(
            EXTRA_FROM_SEARCH,
            false
        )

        if (isFromSearch) {
            getDetailFromNetwork(type, id)
        } else {
            getDetailFromLocal(type, id)
        }
    }

    private fun getDetailFromNetwork(type: String?, id: Int) {
        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            startObservingDetailMovieFromNetwork(id)
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            startObservingDetailTvShowFromNetwork(id)
        }
    }

    private fun getDetailFromLocal(type: String?, id: Int) {
        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            startObservingDetailMovieFromLocal(id)
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            startObservingDetailTvShowFromLocal(id)
        }
    }

    private fun startObservingDetailMovieFromNetwork(id: Int) {
        viewModel.getDetailMovieByIdFromNetwork(id).observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        setUpToobar(movie.data?.title)
                        bindViewMovies(movie.data as Movie)
                    }
                    is Resource.Error -> {
                        movie.message?.let { binding.root.showSnackbar(it) }
                    }
                }
            }
        }
    }

    private fun startObservingDetailTvShowFromNetwork(id: Int) {
        viewModel.getDetailTvShowByIdFromNetwork(id).observe(this) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Success -> {
                        setUpToobar(tvShow.data?.name)
                        bindViewTvShows(tvShow.data as TvShow)
                    }
                    is Resource.Error -> {
                        tvShow.message?.let { binding.root.showSnackbar(it) }
                    }
                }
            }
        }
    }

    private fun startObservingDetailMovieFromLocal(id: Int) {
        viewModel.getDetailMovieById(id).observe(this) { movie ->
            setUpToobar(movie.title)
            bindViewMovies(movie)
        }
    }

    private fun startObservingDetailTvShowFromLocal(id: Int) {
        viewModel.getDetailTvShowById(id).observe(this) { tvShow ->
            setUpToobar(tvShow.name)
            bindViewTvShows(tvShow)
        }
    }

    private fun bindViewTvShows(tvShow: TvShow) {
        binding.imageMovieBackdrop.loadImageUrl(com.ahmaddudayef.moviesmade.core.data.Image.IMAGE_URL + com.ahmaddudayef.moviesmade.core.data.Image.SIZE + tvShow.backdropPath)
        binding.posterMovie.loadImageUrl(com.ahmaddudayef.moviesmade.core.data.Image.IMAGE_URL + com.ahmaddudayef.moviesmade.core.data.Image.SIZE + tvShow.posterPath)
        binding.titleMovie.text = tvShow.name
        binding.ratingMovie.text = tvShow.voteAverage.toString()
        binding.releaseDateMovie.text = tvShow.firstAirDate
        binding.descriptionMovie.text = tvShow.overview
        isFavorite = tvShow.isFavorite
        tvShowCopy = tvShow
    }

    private fun setUpToobar(title: String?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun bindViewMovies(movie: Movie) {
        binding.imageMovieBackdrop.loadImageUrl(com.ahmaddudayef.moviesmade.core.data.Image.IMAGE_URL + com.ahmaddudayef.moviesmade.core.data.Image.SIZE + movie.backdropPath)
        binding.posterMovie.loadImageUrl(com.ahmaddudayef.moviesmade.core.data.Image.IMAGE_URL + com.ahmaddudayef.moviesmade.core.data.Image.SIZE + movie.posterPath)
        binding.titleMovie.text = movie.title
        binding.ratingMovie.text = movie.voteAverage.toString()
        binding.releaseDateMovie.text = movie.releaseDate
        binding.descriptionMovie.text = movie.overview
        isFavorite = movie.isFavorite
        movieCopy = movie
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteVisibility(menuItem)
        setFavoriteState(menuItem)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavoriteVisibility(menuItem: Menu?) {
        val favoriteItem = menuItem?.findItem(R.id.add_to_favorite)
        if (isFromSearch) {
            favoriteItem?.isVisible = false
        }
    }

    private fun setFavoriteState(menu: Menu?) {
        if (isFavorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_unselected)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.add_to_favorite -> {
                setFavorite(movieCopy, tvShowCopy)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(movie: Movie?, tvShow: TvShow?) {
        if (movie != null) {
            if (movie.isFavorite) {
                binding.root.showSnackbar("${movie.title} Removed from favorite")
            } else {
                binding.root.showSnackbar("${movie.title} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite) {
                    binding.root.showSnackbar("${tvShow.name} Removed from favorite")
                } else {
                    binding.root.showSnackbar("${tvShow.name} Added to favorite")
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_FROM_SEARCH = "extra_from_search"
    }
}