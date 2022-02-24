package com.ahmaddudayef.moviesmade.core.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.core.data.Image
import com.ahmaddudayef.moviesmade.core.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.util.loadImageUrl

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.MovieViewHolder>() {
    private val listMovie = ArrayList<Movie>()
    var clickMovieListener: (Movie) -> Unit = {}

    fun setListMovie(listMovieResponse: List<Movie>) {
        val diffCallback = MovieDiffCallback(this.listMovie, listMovieResponse)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listMovie.clear()
        this.listMovie.addAll(listMovieResponse)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            clickMovieListener(data)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieResponse: Movie) {
            binding.tvTitleFilm.text = movieResponse.title
            binding.tvDescriptionFilm.text = movieResponse.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movieResponse.posterPath)
        }
    }
}