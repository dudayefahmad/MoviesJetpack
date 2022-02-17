package com.ahmaddudayef.moviesmade.presentation.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.Type.TYPE_MOVIE
import com.ahmaddudayef.moviesmade.data.Type.TYPE_TVSHOW
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.vo.StatusResponse
import com.ahmaddudayef.moviesmade.databinding.ActivityDetailBinding
import com.ahmaddudayef.moviesmade.util.DataMapper
import com.ahmaddudayef.moviesmade.util.loadImageUrl
import com.ahmaddudayef.moviesmade.util.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()
    private var isFavorite = false
    private var menuItem: Menu? = null
    private var type: String? = null
    private var isFromSearch: Boolean = false
    private var movieEntityCopy: MovieEntity? = null
    private var tvShowEntityCopy: TvShowEntity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        type = intent.getStringExtra(EXTRA_TYPE)
        isFromSearch = intent.getBooleanExtra(EXTRA_FROM_SEARCH, false)

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
                when (movie.status) {
                    StatusResponse.SUCCESS -> {
                        if (movie.body != null) {
                            setUpToobar(movie.body.title)
                            bindViewMovies(DataMapper.mapMovieToMovieEntity(movie.body))
                        }
                    }

                    StatusResponse.ERROR -> {
                        movie.message?.let { binding.root.showSnackbar(it) }
                    }
                }
            }
        }
    }

    private fun startObservingDetailTvShowFromNetwork(id: Int) {
        viewModel.getDetailTvShowByIdFromNetwork(id).observe(this) { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    StatusResponse.SUCCESS -> {
                        if (tvShow.body != null) {
                            setUpToobar(tvShow.body.name)
                            bindViewTvShows(DataMapper.mapTvShowToTvShowEntity(tvShow.body))
                        }
                    }

                    StatusResponse.ERROR -> {
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

    private fun bindViewTvShows(tvShowEntity: TvShowEntity) {
        binding.imageMovieBackdrop.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShowEntity.backdropPath)
        binding.posterMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShowEntity.posterPath)
        binding.titleMovie.text = tvShowEntity.name
        binding.ratingMovie.text = tvShowEntity.voteAverage.toString()
        binding.releaseDateMovie.text = tvShowEntity.firstAirDate
        binding.descriptionMovie.text = tvShowEntity.overview
        isFavorite = tvShowEntity.isFavorite
        tvShowEntityCopy = tvShowEntity
    }

    private fun setUpToobar(title: String?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun bindViewMovies(movieEntity: MovieEntity) {
        binding.imageMovieBackdrop.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movieEntity.backdropPath)
        binding.posterMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movieEntity.posterPath)
        binding.titleMovie.text = movieEntity.title
        binding.ratingMovie.text = movieEntity.voteAverage.toString()
        binding.releaseDateMovie.text = movieEntity.releaseDate
        binding.descriptionMovie.text = movieEntity.overview
        isFavorite = movieEntity.isFavorite
        movieEntityCopy = movieEntity
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
                setFavorite(movieEntityCopy, tvShowEntityCopy)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
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
                    binding.root.showSnackbar("${tvShow.name} Aemoved from favorite")
                } else {
                    binding.root.showSnackbar("${tvShow.name} Removed from favorite")
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