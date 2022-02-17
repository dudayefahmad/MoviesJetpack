package com.ahmaddudayef.moviesmade.presentation.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.Type
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.util.loadImageUrl

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.MovieViewHolder>() {
    private val listMovie = ArrayList<Movie>()

    fun setListMovie(listMovie: List<Movie>) {
        val diffCallback = MovieDiffCallback(this.listMovie, listMovie)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
        holder.itemView.setOnClickListener {
            val data = listMovie[position]
            val context = it.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_MOVIE)
            intent.putExtra(DetailActivity.EXTRA_FROM_SEARCH, true)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitleFilm.text = movie.title
            binding.tvDescriptionFilm.text = movie.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movie.posterPath)
        }
    }
}