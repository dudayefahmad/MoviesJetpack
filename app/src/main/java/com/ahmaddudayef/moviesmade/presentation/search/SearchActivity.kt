package com.ahmaddudayef.moviesmade.presentation.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.databinding.ActivitySearchBinding
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import com.ahmaddudayef.moviesmade.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModel<SearchViewModel>()
    private val movieAdapter by lazy { SearchMovieAdapter() }
    private val tvShowAdapter by lazy { SearchTvShowAdapter() }
    private lateinit var query: String
    val EXTRA_QUERY = "extra_query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        query = intent.getStringExtra(EXTRA_QUERY)!!
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
        viewModel.searchMovieState.observe(this) { movies ->
            when (movies.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    setMovieData(movies.data)
                }
                Status.ERROR -> {
                    hideLoading()
                    showError(movies.message)
                }
            }

            viewModel.searchTvShowState.observe(this) { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        setTvShowData(tvShow.data)
                    }
                    Status.ERROR -> {
                        hideLoading()
                        showError(movies.message)
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
        } else if (binding.spinnerSearch.selectedItemPosition == 1) {
            binding.rvSearch.adapter = tvShowAdapter
        }

    }

    private fun setTvShowData(data: List<TvShow>?) {
        if (data != null) {
            tvShowAdapter.setListTvShow(data)
        }
    }


    private fun setMovieData(data: List<Movie>?) {
        if (data != null) {
            movieAdapter.setListMovie(data)
        }
    }

    private fun searchMovie() {
        setUpRecyclerView()
        viewModel.searchMovie(query)
    }

    private fun searchTvShow() {
        setUpRecyclerView()
        viewModel.searchTvShow(query)
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
