package com.ahmaddudayef.moviesmade.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ahmaddudayef.moviesmade.favorite.movie.MovieFavoriteFragment
import com.ahmaddudayef.moviesmade.favorite.tvshow.TvShowFavoriteFragment


private const val NUM_TABS = 2

class ViewPagerFavoriteAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFavoriteFragment()
            1 -> return TvShowFavoriteFragment()
        }
        return Fragment()
    }

}