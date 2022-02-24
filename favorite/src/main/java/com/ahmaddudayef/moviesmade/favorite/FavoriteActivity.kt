package com.ahmaddudayef.moviesmade.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ahmaddudayef.moviesmade.favorite.databinding.ActivityFavoriteBinding
import com.ahmaddudayef.moviesmade.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_favorite)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerFavoriteAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerFavorite.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabsFavorite, binding.viewPagerFavorite) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_movies)
                1 -> tab.text = getString(R.string.tab_tv_show)
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}