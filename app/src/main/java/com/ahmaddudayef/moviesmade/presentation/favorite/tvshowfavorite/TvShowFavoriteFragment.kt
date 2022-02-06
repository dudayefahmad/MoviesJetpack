package com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.databinding.FragmentTvShowFavoriteBinding
import com.ahmaddudayef.moviesmade.presentation.home.tvshow.TvShowAdapter
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class TvShowFavoriteFragment : Fragment() {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TvShowFavoriteViewModel>()
    private val adapter by lazy { TvShowAdapter() }
    private var listTvShow = arrayListOf<TvShow>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveValueOrientationChange(savedInstanceState)
        setUpRecyclerView()
        startObservingTvShowData()
        setupSwipeRefreshLayout()
        viewModel.getFavoriteTvShow()
    }

    private fun setupSwipeRefreshLayout() {
        binding.srlRootTvShow.setOnRefreshListener {
            viewModel.getFavoriteTvShow()
        }
    }

    private fun saveValueOrientationChange(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            listTvShow =
                savedInstanceState.getParcelableArrayList<TvShow>("DATA_TVSHOW") as ArrayList<TvShow>
        }
    }

    private fun setUpRecyclerView() {
        binding.rvTvShowFavorite.layoutManager = LinearLayoutManager(activity)
        binding.rvTvShowFavorite.setHasFixedSize(true)
        binding.rvTvShowFavorite.adapter = adapter
    }

    private fun startObservingTvShowData() {
        viewModel.tvShowFavoriteState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Loading -> {
                    showLoading()
                }

                is State.Error -> {
                    hideLoading()
                    showError(it.throwable)
                }

                is State.Success -> {
                    hideLoading()
                    setTvShowData(it.data)
                }
            }
        }
    }

    private fun setTvShowData(data: List<TvShow>) {
        adapter.setListTvShow(data)
    }

    private fun showError(throwable: Throwable) {
        throwable.message?.let { activity?.showSnackbar(binding.clRoot, it) }
    }

    private fun hideLoading() {
        binding.srlRootTvShow.isRefreshing = false
        binding.progressView.gone()
        binding.rvTvShowFavorite.visible()
    }

    private fun showLoading() {
        binding.progressView.visible()
        binding.rvTvShowFavorite.gone()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("DATA_TVSHOW", listTvShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
