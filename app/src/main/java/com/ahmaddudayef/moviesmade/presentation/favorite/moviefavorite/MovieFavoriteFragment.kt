package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmaddudayef.moviesmade.R
import com.ahmaddudayef.moviesmade.data.Type.TYPE_MOVIE
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.databinding.FragmentMovieFavoriteBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.presentation.home.movie.MovieAdapter
import com.ahmaddudayef.moviesmade.presentation.home.movie.MovieCallback
import com.ahmaddudayef.moviesmade.util.gone
import com.ahmaddudayef.moviesmade.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFavoriteFragment : Fragment(), MovieCallback {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModel<MovieFavoriteViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        startObservingMovieData()
    }

    private fun setUpRecyclerView() {
        adapter = MovieAdapter(this)
        binding?.rvMovie?.layoutManager = LinearLayoutManager(activity)
        binding?.rvMovie?.setHasFixedSize(true)
        binding?.rvMovie?.adapter = adapter
    }

    private fun startObservingMovieData() {
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movie ->
            if (movie.isNullOrEmpty()) {
                binding?.rvMovie?.gone()
                enableEmptyStateEmptyFavoriteMovie()
            } else {
                binding?.rvMovie?.visible()
                binding?.favoriteMovieEmptyState?.root?.gone()
                adapter.submitList(movie)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {
        binding?.favoriteMovieEmptyState?.imgEmptyState?.setImageResource(R.drawable.ic_empty_state_favorite)
        binding?.favoriteMovieEmptyState?.imgEmptyState?.contentDescription =
            resources.getString(R.string.empty_state_desc_no_favorite_item_movie)
        binding?.favoriteMovieEmptyState?.titleEmptyState?.text =
            resources.getString(R.string.empty_state_title_no_favorite_item)
        binding?.favoriteMovieEmptyState?.descEmptyState?.text =
            resources.getString(R.string.empty_state_desc_no_favorite_item_movie)
        binding?.favoriteMovieEmptyState?.root?.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}
