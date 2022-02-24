package com.ahmaddudayef.moviesmade.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.data.Type
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.ui.search.SearchMovieAdapter
import com.ahmaddudayef.moviesmade.core.ui.search.SearchTvShowAdapter
import com.ahmaddudayef.moviesmade.core.util.gone
import com.ahmaddudayef.moviesmade.core.util.showSnackbar
import com.ahmaddudayef.moviesmade.core.util.visible
import com.ahmaddudayef.moviesmade.databinding.ActivitySearchBinding
import com.ahmaddudayef.moviesmade.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModel<SearchViewModel>()
    private val movieAdapter by lazy { SearchMovieAdapter() }
    private val tvShowAdapter by lazy { SearchTvShowAdapter() }
    private lateinit var query: String
    val extraQuery = "extra_query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        query = intent.getStringExtra(extraQuery)!!
        setupToolbar()

        val arrayAdapter = ArrayAdapter(
            applicationContext, android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.search_filter_array)
        )

        binding.spinnerSearch.adapter = arrayAdapter
        binding.spinnerSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.progressView.visibility = View.VISIBLE

                if (binding.spinnerSearch.selectedItemPosition == 0) {
                    searchMovie()
                } else if (binding.spinnerSearch.selectedItemPosition == 1) {
                    searchTvShow()
                }
            }
        }
        startObservingData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = query
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = (menu?.findItem(R.id.action_search)?.actionView) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                this@SearchActivity.query = query.toString()
                supportActionBar?.title = query

                if (binding.spinnerSearch.selectedItemPosition == 0) {
                    searchMovie()
                } else if (binding.spinnerSearch.selectedItemPosition == 1) {
                    searchTvShow()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun startObservingData() {
        viewModel.searchMovieResult.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading<*> -> {
                        showLoading()
                    }
                    is Resource.Success<*> -> {
                        hideLoading()
                        setMovieData(movies.data as List<Movie>)
                    }
                    is Resource.Error<*> -> {
                        hideLoading()
                        showError(movies.message)
                    }
                }
            }
        }

        viewModel.searchTvShowResult.observe(this) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading<*> -> {
                        showLoading()
                    }
                    is Resource.Success<*> -> {
                        hideLoading()
                        setTvShowData(tvShow.data as List<TvShow>)
                    }
                    is Resource.Error<*> -> {
                        hideLoading()
                        showError(tvShow.message)
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.setHasFixedSize(true)
        if (binding.spinnerSearch.selectedItemPosition == 0) {
            binding.rvSearch.adapter = movieAdapter
            movieAdapter.clickMovieListener = { movie ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_MOVIE)
                intent.putExtra(DetailActivity.EXTRA_FROM_SEARCH, true)
                startActivity(intent)
            }
        } else if (binding.spinnerSearch.selectedItemPosition == 1) {
            binding.rvSearch.adapter = tvShowAdapter
            tvShowAdapter.clickTvShowListener = { tvShow ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_TVSHOW)
                intent.putExtra(DetailActivity.EXTRA_FROM_SEARCH, true)
                startActivity(intent)
            }
        }

    }

    private fun setTvShowData(data: List<TvShow>) {
        tvShowAdapter.setListTvShow(data)
    }


    private fun setMovieData(data: List<Movie>) {
        movieAdapter.setListMovie(data)
    }

    private fun searchMovie() {
        lifecycleScope.launch {
            setUpRecyclerView()
            viewModel.queryMovieChanel.send(query)
        }
    }

    private fun searchTvShow() {
        lifecycleScope.launch {
            setUpRecyclerView()
            viewModel.queryTvShowChannel.send(query)
        }
    }

    private fun showError(errorMessage: String?) {
        if (errorMessage != null) {
            binding.root.showSnackbar(errorMessage)
        }
    }

    private fun hideLoading() {
        binding.progressView.gone()
        binding.rvSearch.visible()
    }

    private fun showLoading() {
        binding.progressView.visible()
        binding.rvSearch.gone()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("query", query)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        query = savedInstanceState.getString("query").toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
