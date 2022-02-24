package com.ahmaddudayef.moviesmade.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ahmaddudayef.moviesmade.home.movie.MovieFragment
import com.ahmaddudayef.moviesmade.home.tvshow.TvShowFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()
            1 -> return TvShowFragment()
        }
        return Fragment()
    }

}