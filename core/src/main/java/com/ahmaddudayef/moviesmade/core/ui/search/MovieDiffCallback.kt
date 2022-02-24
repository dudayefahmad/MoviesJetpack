package com.ahmaddudayef.moviesmade.core.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.ahmaddudayef.moviesmade.core.domain.model.Movie

class MovieDiffCallback(
    private val mOldListMovieResponse: List<Movie>,
    private val mNewListMovieResponse: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListMovieResponse.size
    }

    override fun getNewListSize(): Int {
        return mNewListMovieResponse.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListMovieResponse[oldItemPosition].id == mNewListMovieResponse[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = mOldListMovieResponse[oldItemPosition]
        val newMovie = mNewListMovieResponse[newItemPosition]
        return oldMovie.title == newMovie.title && oldMovie.overview == newMovie.overview
    }
}