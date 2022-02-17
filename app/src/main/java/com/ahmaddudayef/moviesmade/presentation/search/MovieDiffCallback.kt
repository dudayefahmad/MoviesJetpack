package com.ahmaddudayef.moviesmade.presentation.search

import androidx.recyclerview.widget.DiffUtil
import com.ahmaddudayef.moviesmade.data.remote.response.Movie

class MovieDiffCallback(
    private val mOldListMovie: List<Movie>,
    private val mNewListMovie: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListMovie.size
    }

    override fun getNewListSize(): Int {
        return mNewListMovie.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListMovie[oldItemPosition].id == mNewListMovie[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = mOldListMovie[oldItemPosition]
        val newMovie = mNewListMovie[newItemPosition]
        return oldMovie.title == newMovie.title && oldMovie.overview == newMovie.overview
    }
}