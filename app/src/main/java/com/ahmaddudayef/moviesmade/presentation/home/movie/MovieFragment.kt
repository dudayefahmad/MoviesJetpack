package com.ahmaddudayef.moviesmade.presentation.home.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.Type
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.databinding.FragmentMovieBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import com.ahmaddudayef.moviesmade.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<MovieViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        startObservingMovieData()
    }

    private fun startObservingMovieData() {
        viewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        setMovieData(movies.data)
                    }
                    Status.ERROR -> {
                        hideLoading()
                        showError(movies.message)
                    }
                }
            }
        }
    }

    private fun setMovieData(data: PagedList<MovieEntity>?) {
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
        binding?.rvMovie?.visible()
    }

    private fun showLoading() {
        binding?.progressView?.visible()
        binding?.rvMovie?.gone()
    }

    private fun setUpRecyclerView() {
        adapter = MovieAdapter(this)
        binding?.rvMovie?.layoutManager = LinearLayoutManager(activity)
        binding?.rvMovie?.setHasFixedSize(true)
        binding?.rvMovie?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_MOVIE)
        startActivity(intent)
    }

}
