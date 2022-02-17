package com.ahmaddudayef.moviesmade.presentation.home.tvshow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.Type
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.databinding.FragmentTvShowBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import com.ahmaddudayef.moviesmade.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(), TvShowCallback {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<TvShowViewModel>()
    private lateinit var adapter: TvShowAdapter

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
                when (tvShows.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        setTvShowData(tvShows.data)
                    }
                    Status.ERROR -> {
                        hideLoading()
                        showError(tvShows.message)
                    }
                }
            }
        }
    }

    private fun setTvShowData(data: PagedList<TvShowEntity>?) {
        adapter.submitList(data)
        adapter.notifyDataSetChanged()
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
        adapter = TvShowAdapter(this)
        binding?.rvTvShow?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTvShow?.setHasFixedSize(true)
        binding?.rvTvShow?.adapter = adapter
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
