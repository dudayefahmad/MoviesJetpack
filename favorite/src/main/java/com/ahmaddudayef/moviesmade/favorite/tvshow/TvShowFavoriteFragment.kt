package com.ahmaddudayef.moviesmade.favorite.tvshow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.core.data.Type
import com.ahmaddudayef.moviesmade.core.ui.tvshow.TvShowAdapter
import com.ahmaddudayef.moviesmade.core.util.gone
import com.ahmaddudayef.moviesmade.core.util.visible
import com.ahmaddudayef.moviesmade.detail.DetailActivity
import com.ahmaddudayef.moviesmade.favorite.R
import com.ahmaddudayef.moviesmade.favorite.databinding.FragmentTvShowFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvShowFavoriteFragment : Fragment() {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<TvShowFavoriteViewModel>()
    private val adapter by lazy { TvShowAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        startObservingTvShowData()
    }

    private fun setUpRecyclerView() {
        binding?.rvTvShowFavorite?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTvShowFavorite?.setHasFixedSize(true)
        binding?.rvTvShowFavorite?.adapter = adapter
        adapter.clickTvShowListener = { tvShow ->
            val intent =
                Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
            intent.putExtra(
                DetailActivity.EXTRA_TYPE,
                Type.TYPE_TVSHOW
            )
            startActivity(intent)
        }
    }

    private fun startObservingTvShowData() {
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { tvShow ->
            if (tvShow.isNullOrEmpty()) {
                binding?.rvTvShowFavorite?.gone()
                enableEmptyStateEmptyFavoriteMovie()
            } else {
                binding?.rvTvShowFavorite?.visible()
                binding?.favoriteTvShowEmptyState?.root?.gone()
                adapter.setListTvShow(tvShow)
            }
        }
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {
        binding?.favoriteTvShowEmptyState?.imgEmptyState?.setImageResource(R.drawable.ic_empty_state_favorite)
        binding?.favoriteTvShowEmptyState?.imgEmptyState?.contentDescription =
            resources.getString(R.string.empty_state_desc_no_favorite_item_movie)
        binding?.favoriteTvShowEmptyState?.titleEmptyState?.text =
            resources.getString(R.string.empty_state_title_no_favorite_item)
        binding?.favoriteTvShowEmptyState?.descEmptyState?.text =
            resources.getString(R.string.empty_state_desc_no_favorite_item_tvshow)
        binding?.favoriteTvShowEmptyState?.root?.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
