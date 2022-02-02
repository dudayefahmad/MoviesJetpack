package com.ahmaddudayef.moviesmade.presentation.detaimovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.databinding.ActivityDetailBinding
import com.ahmaddudayef.moviesmade.util.loadImageUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_FILM = "detail_film"
    }

    private lateinit var binding: ActivityDetailBinding
    lateinit var movie: Movie
    private val viewModel by viewModel<DetailMovieViewModel>()
    private var isFavorite = false
    private var menuItem: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        startObservingData()
        movie.id?.let { viewModel.getSingleMovie(it) }
    }

    private fun startObservingData() {
        viewModel.favoriteMovieState.observe(this) {
            when (it) {
                is State.Error -> {
                    Toast.makeText(this, "Gagal simpan movie", Toast.LENGTH_SHORT).show()
                }
                is State.Success -> {
                    menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
                    isFavorite = true
                    Toast.makeText(this, "Berhasil simpan movie", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.deleteFavoriteMovieState.observe(this) {
            when (it) {
                is State.Error -> {
                    Toast.makeText(this, "Gagal hapus movie dari favorit", Toast.LENGTH_SHORT)
                        .show()
                }
                is State.Success -> {
                    menuItem?.getItem(0)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_star_unselected)
                    isFavorite = false
                    Toast.makeText(this, "Berhasil menhapus movie dari favorit", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        viewModel.getSingleMovieState.observe(this) {
            when (it) {
                is State.Error -> {

                }
                is State.Success -> {
                    setFavoriteData(it.data)
                }
            }
        }
    }

    private fun setFavoriteData(data: Movie?) {
        if (data?.id != null) {
            isFavorite = true
        }
    }

    private fun getIntentData() {
        movie = intent.getParcelableExtra(DETAIL_FILM)!!
        setUpToobarMovies()
        bindViewMovies()
    }

    private fun setUpToobarMovies() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movie.title
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun bindViewMovies() {
        binding.imageMovieBackdrop.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movie.backdropPath)
        binding.posterMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movie.posterPath)
        binding.titleMovie.text = movie.title
        binding.ratingMovie.text = movie.voteAverage.toString()
        binding.releaseDateMovie.text = movie.releaseDate
        binding.descriptionMovie.text = movie.overview
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite(menuItem)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite(menu: Menu?) {
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
                if (isFavorite) {
                    viewModel.deleteMovie(movie)
                } else {
                    viewModel.saveMovie(movie)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}