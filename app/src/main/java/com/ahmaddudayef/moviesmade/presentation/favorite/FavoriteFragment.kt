package com.ahmaddudayef.moviesmade.presentation.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = ViewPagerFavoriteAdapter(childFragmentManager, lifecycle)
        binding.viewPagerFavoite.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabsFavorite, binding.viewPagerFavoite) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_movies)
                1 -> tab.text = getString(R.string.tab_tv_show)
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
