package com.ahmaddudayef.moviesmade.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ahmaddudayef.moviesmade.presentation.favorite.FavoriteFragment
import com.ahmaddudayef.moviesmade.presentation.home.movie.MovieFragment
import com.ahmaddudayef.moviesmade.presentation.home.tvshow.TvShowFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()
            1 -> return TvShowFragment()
            2 -> return FavoriteFragment()
        }
        return Fragment()
    }

}