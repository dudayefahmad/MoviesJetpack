package com.ahmaddudayef.moviesmade.presentation.home.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.databinding.FragmentMovieBinding
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<MovieViewModel>()
    private val adapter by lazy { MovieAdapter() }
    private var listMovie = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDataByLanguange(savedInstanceState)
        setUpRecyclerView()
        startObservingMovieData()
        setupSwipeRefreshLayout()
    }

    private fun setupSwipeRefreshLayout() {
        binding?.srlMovie?.setOnRefreshListener {
            getMovies()
        }
    }

    private fun setDataByLanguange(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            listMovie =
                savedInstanceState.getParcelableArrayList<Movie>("DATA_MOVIE") as ArrayList<Movie>
        } else {
            getMovies()
        }
    }

    private fun getMovies() {
        val language = Locale.getDefault().toString()
        if (language == "id" || language == "in_ID") {
            viewModel.getMovies("id")
        } else {
            viewModel.getMovies("en")
        }
    }

    private fun startObservingMovieData() {
        viewModel.movieState.observe(viewLifecycleOwner) {
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

    private fun setMovieData(data: List<Movie>) {
        adapter.setListMovie(data)
    }

    private fun showError(throwable: Throwable) {
        throwable.message?.let { binding?.let { it1 -> activity?.showSnackbar(it1.clRoot, it) } }
    }

    private fun hideLoading() {
        binding?.srlMovie?.isRefreshing = false
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("DATA_MOVIE", listMovie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
