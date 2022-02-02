package com.ahmaddudayef.moviesmade.presentation.home.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.presentation.detaimovie.DetailMovieActivity
import com.ahmaddudayef.moviesmade.util.loadImageUrl

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var listMovie: List<Movie> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
        holder.itemView.setOnClickListener {
            val data = listMovie[position]
            val context = it.context
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.DETAIL_FILM, data)
            context.startActivity(intent)
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