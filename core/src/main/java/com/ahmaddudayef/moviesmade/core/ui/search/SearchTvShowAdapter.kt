package com.ahmaddudayef.moviesmade.core.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.core.data.Image
import com.ahmaddudayef.moviesmade.core.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.util.loadImageUrl

class SearchTvShowAdapter : RecyclerView.Adapter<SearchTvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShow>()
    var clickTvShowListener: (TvShow) -> Unit = {}

    fun setListTvShow(listTvShowResponse: List<TvShow>) {
        val diffCallback = TvShowDiffCallback(this.listTvShow, listTvShowResponse)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listTvShow.clear()
        this.listTvShow.addAll(listTvShowResponse)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listTvShow[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            clickTvShowListener(data)
        }
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShowResponse: TvShow) {
            binding.tvTitleFilm.text = tvShowResponse.name
            binding.tvDescriptionFilm.text = tvShowResponse.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShowResponse.posterPath)
        }
    }
}