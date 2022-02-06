package com.ahmaddudayef.moviesmade.presentation.home.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.databinding.FragmentTvShowBinding
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TvShowViewModel>()
    private val adapter by lazy { TvShowAdapter() }
    private var listTvShow = arrayListOf<TvShow>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDataByLanguange(savedInstanceState)
        setUpRecyclerView()
        startObservingTvShowData()
        setupSwipeRefreshLayout()
    }

    private fun setupSwipeRefreshLayout() {
        binding.srlTvShow.setOnRefreshListener {
            getTvShow()
        }
    }

    private fun setDataByLanguange(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            listTvShow =
                savedInstanceState.getParcelableArrayList<TvShow>("DATA_TV_SHOW") as ArrayList<TvShow>
        } else {
            getTvShow()
        }
    }

    private fun getTvShow() {
        val language = Locale.getDefault().toString()
        if (language == "id" || language == "in_ID") {
            viewModel.getTvShow("id")
        } else {
            viewModel.getTvShow("en")
        }
    }

    private fun startObservingTvShowData() {
        viewModel.tvShowState.observe(viewLifecycleOwner) {
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
                    setMovieData(it.data)
                }
            }
        }
    }

    private fun setMovieData(data: List<TvShow>) {
        adapter.setListTvShow(data)
    }

    private fun showError(throwable: Throwable) {
        throwable.message?.let { activity?.showSnackbar(binding.clRoot, it) }
    }

    private fun hideLoading() {
        binding.srlTvShow.isRefreshing = false
        binding.progressView.gone()
        binding.rvTvShow.visible()
    }

    private fun showLoading() {
        binding.progressView.visible()
        binding.rvTvShow.gone()
    }

    private fun setUpRecyclerView() {
        binding.rvTvShow.layoutManager = LinearLayoutManager(activity)
        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("DATA_TV_SHOW", listTvShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
