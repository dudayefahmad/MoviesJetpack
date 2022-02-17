package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.data.Type
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.databinding.FragmentTvShowFavoriteBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.presentation.home.tvshow.TvShowAdapter
import com.ahmaddudayef.moviesmade.presentation.home.tvshow.TvShowCallback
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvShowFavoriteFragment : Fragment(), TvShowCallback {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<TvShowFavoriteViewModel>()
    private lateinit var adapter: TvShowAdapter

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
        adapter = TvShowAdapter(this)
        binding?.rvTvShowFavorite?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTvShowFavorite?.setHasFixedSize(true)
        binding?.rvTvShowFavorite?.adapter = adapter
    }

    private fun startObservingTvShowData() {
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { tvShow ->
            if (tvShow.isNullOrEmpty()) {
                binding?.rvTvShowFavorite?.gone()
                enableEmptyStateEmptyFavoriteMovie()
            } else {
                binding?.rvTvShowFavorite?.visible()
                binding?.favoriteTvShowEmptyState?.root?.gone()
                adapter.submitList(tvShow)
                adapter.notifyDataSetChanged()
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

    override fun onItemClicked(data: TvShowEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_TVSHOW)
        startActivity(intent)
    }


}
