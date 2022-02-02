package com.ahmaddudayef.moviesmade.presentation.detailtvshow

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
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.databinding.ActivityDetailBinding
import com.ahmaddudayef.moviesmade.util.loadImageUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_TVSHOW = "detail_tv_show"
    }

    private lateinit var binding: ActivityDetailBinding
    lateinit var tvShow: TvShow
    private val viewModel by viewModel<DetailTvShowViewModel>()
    private var isFavorite = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        startObservingData()
        tvShow.id?.let { viewModel.getSingleLocalTvShow(it) }
    }

    private fun startObservingData() {
        viewModel.favoriteTvShowState.observe(this) {
            when (it) {
                is State.Error -> {
                    Toast.makeText(this, "Gagal simpan tvshow", Toast.LENGTH_SHORT).show()
                }
                is State.Success -> {
                    menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
                    isFavorite = true
                    Toast.makeText(this, "Berhasil simpan tvShow", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.deleteFavoriteTvShowState.observe(this) {
            when (it) {
                is State.Error -> {
                    Toast.makeText(this, "Gagal hapus tvShow dari favorit", Toast.LENGTH_SHORT)
                        .show()
                }
                is State.Success -> {
                    menuItem?.getItem(0)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_star_unselected)
                    isFavorite = false
                    Toast.makeText(this, "Berhasil menhapus tvShow dari favorit", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        viewModel.getSingleTvShowState.observe(this) {
            when (it) {
                is State.Error -> {

                }
                is State.Success -> {
                    setFavoriteByData(it.data)
                }
            }
        }
    }

    private fun setFavoriteByData(data: TvShow?) {
        if (data?.id != null) {
            isFavorite = true
        }
    }

    private fun getIntentData() {
        tvShow = intent.getParcelableExtra(DETAIL_TVSHOW)!!
        setUpToobarTvShow()
        bindViewTvShow()
    }

    private fun setUpToobarTvShow() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = tvShow.name
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun bindViewTvShow() {
        binding.imageMovieBackdrop.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShow.backdropPath)
        binding.posterMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShow.posterPath)
        binding.titleMovie.text = tvShow.name
        binding.ratingMovie.text = tvShow.voteAverage.toString()
        binding.releaseDateMovie.text = tvShow.firstAirDate
        binding.descriptionMovie.text = tvShow.overview
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
                    viewModel.deleteTvShow(tvShow)
                } else {
                    viewModel.saveTvShow(tvShow)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

