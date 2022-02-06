package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import androidx.recyclerview.widget.DiffUtil
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

class TvShowDiffCallback(
    private val mOldListTvShow: List<TvShow>,
    private val mNewListTvShow: List<TvShow>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldListTvShow.size
    }

    override fun getNewListSize(): Int {
        return mNewListTvShow.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldListTvShow[oldItemPosition].id == mNewListTvShow[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTvShow = mOldListTvShow[oldItemPosition]
        val newTvShow = mNewListTvShow[newItemPosition]
        return oldTvShow.name == newTvShow.name && oldTvShow.overview == newTvShow.overview
    }
}