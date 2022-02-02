package com.ahmaddudayef.moviesmade.presentation.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.databinding.ActivityMainBinding
import com.ahmaddudayef.moviesmade.presentation.search.SearchActivity
import com.ahmaddudayef.moviesmade.presentation.setting.SettingsActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startObservingData()
        setUpViewPager()
    }

    private fun startObservingData() {
        lifecycleScope.launch {
            viewModel.getThemeSettings().observe(this@HomeActivity) { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    private fun setUpViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_movies)
                1 -> tab.text = getString(R.string.tab_tv_show)
                2 -> tab.text = getString(R.string.tab_favorites)
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = (menu?.findItem(R.id.action_search)?.actionView) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra(SearchActivity().EXTRA_QUERY, query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
