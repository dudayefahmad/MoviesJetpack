package com.ahmaddudayef.moviesmade.home.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.ui.movie.MovieAdapter
import com.ahmaddudayef.moviesmade.databinding.FragmentMovieBinding
import com.ahmaddudayef.moviesmade.core.util.gone
import com.ahmaddudayef.moviesmade.core.util.showSnackbar
import com.ahmaddudayef.moviesmade.core.util.visible
import com.ahmaddudayef.moviesmade.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<MovieViewModel>()
    private val adapter by lazy { MovieAdapter() }

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
                when (movies) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        setMovieData(movies.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showError(movies.message)
                    }
                }
            }
        }
    }

    private fun setMovieData(data: List<Movie>?) {
        adapter.setListMovie(data)
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
        binding?.rvMovie?.layoutManager = LinearLayoutManager(activity)
        binding?.rvMovie?.setHasFixedSize(true)
        binding?.rvMovie?.adapter = adapter
        adapter.clickMovieListener = { movie ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, com.ahmaddudayef.moviesmade.core.data.Type.TYPE_MOVIE)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
