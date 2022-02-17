package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.util.loadImageUrl

class TvShowAdapter(private val callback: TvShowCallback) :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
        holder.itemView.setOnClickListener {
            tvShow?.let { tvShow ->
                callback.onItemClicked(tvShow)
            }
        }
    }

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitleFilm.text = tvShow.name
                tvDescriptionFilm.text = tvShow.overview
                imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShow.posterPath)
            }
        }
    }

}