package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.data.State
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.databinding.FragmentMovieFavoriteBinding
import com.ahmaddudayef.moviesmade.presentation.home.movie.MovieAdapter
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.showSnackbar
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFavoriteFragment : Fragment() {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MovieFavoriteViewModel>()
    private val adapter by lazy { MovieAdapter() }
    private var listMovie = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveValueOrientationChange(savedInstanceState)
        setUpRecyclerView()
        startObservingMovieData()
        setupSwipeRefreshLayout()
        viewModel.getFavoriteMovie()
    }

    private fun setupSwipeRefreshLayout() {
        binding.srlRootMovie.setOnRefreshListener {
            viewModel.getFavoriteMovie()
        }
    }

    private fun saveValueOrientationChange(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            listMovie =
                savedInstanceState.getParcelableArrayList<Movie>("DATA_MOVIE") as ArrayList<Movie>
        }
    }

    private fun setUpRecyclerView() {
        binding.rvMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMovie.adapter = adapter
    }

    private fun startObservingMovieData() {
        viewModel.movieFavoriteState.observe(viewLifecycleOwner) {
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
        listMovie.clear()
        listMovie.addAll(data)
        adapter.listMovie = listMovie
    }

    private fun showError(throwable: Throwable) {
        throwable.message?.let { activity?.showSnackbar(binding.clRoot, it) }
    }

    private fun hideLoading() {
        binding.srlRootMovie.isRefreshing = false
        binding.progressView.gone()
        binding.rvMovie.visible()
    }

    private fun showLoading() {
        binding.progressView.visible()
        binding.rvMovie.gone()
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
