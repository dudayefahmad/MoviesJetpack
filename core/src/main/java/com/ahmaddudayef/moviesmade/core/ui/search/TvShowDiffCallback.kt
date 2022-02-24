package com.ahmaddudayef.moviesmade.core.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow

class TvShowDiffCallback(
    private val mOldListTvShowResponse: List<TvShow>,
    private val mNewListTvShowResponse: List<TvShow>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListTvShowResponse.size
    }

    override fun getNewListSize(): Int {
        return mNewListTvShowResponse.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListTvShowResponse[oldItemPosition].id == mNewListTvShowResponse[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTvShow = mOldListTvShowResponse[oldItemPosition]
        val newTvShow = mNewListTvShowResponse[newItemPosition]
        return oldTvShow.name == newTvShow.name && oldTvShow.overview == newTvShow.overview
    }
}