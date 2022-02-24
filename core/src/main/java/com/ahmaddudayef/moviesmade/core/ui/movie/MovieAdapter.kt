package com.ahmaddudayef.moviesmade.core.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.core.data.Image
import com.ahmaddudayef.moviesmade.core.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.util.loadImageUrl

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<Movie>()
    var clickMovieListener: (Movie) -> Unit = {}

    fun setListMovie(listMovie: List<Movie>?) {
        if (listMovie == null) return
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

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            clickMovieListener(data)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitleFilm.text = movie.title
            binding.tvDescriptionFilm.text = movie.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + movie.posterPath)
        }
    }


}