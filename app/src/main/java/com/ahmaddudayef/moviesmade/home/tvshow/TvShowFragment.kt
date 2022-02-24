package com.ahmaddudayef.moviesmade.home.tvshow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.ui.tvshow.TvShowAdapter
import com.ahmaddudayef.moviesmade.core.util.gone
import com.ahmaddudayef.moviesmade.core.util.showSnackbar
import com.ahmaddudayef.moviesmade.core.util.visible
import com.ahmaddudayef.moviesmade.databinding.FragmentTvShowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<TvShowViewModel>()
    private val adapter by lazy { TvShowAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        startObservingTvShowData()
    }

    private fun startObservingTvShowData() {
        viewModel.getTvShow().observe(viewLifecycleOwner) { tvShows ->
            if (tvShows != null) {
                when (tvShows) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        setTvShowData(tvShows.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showError(tvShows.message)
                    }
                }
            }
        }
    }

    private fun setTvShowData(data: List<TvShow>?) {
        adapter.setListTvShow(data)
    }

    private fun showError(errorMessage: String?) {
        if (errorMessage != null) {
            binding?.root?.showSnackbar(errorMessage)
        }
    }

    private fun hideLoading() {
        binding?.progressView?.gone()
        binding?.rvTvShow?.visible()
    }

    private fun showLoading() {
        binding?.progressView?.visible()
        binding?.rvTvShow?.gone()
    }

    private fun setUpRecyclerView() {
        binding?.rvTvShow?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTvShow?.setHasFixedSize(true)
        binding?.rvTvShow?.adapter = adapter
        adapter.clickTvShowListener = { tvShow ->
            val intent =
                Intent(context, com.ahmaddudayef.moviesmade.detail.DetailActivity::class.java)
            intent.putExtra(com.ahmaddudayef.moviesmade.detail.DetailActivity.EXTRA_DATA, tvShow.id)
            intent.putExtra(
                com.ahmaddudayef.moviesmade.detail.DetailActivity.EXTRA_TYPE,
                com.ahmaddudayef.moviesmade.core.data.Type.TYPE_TVSHOW
            )
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
