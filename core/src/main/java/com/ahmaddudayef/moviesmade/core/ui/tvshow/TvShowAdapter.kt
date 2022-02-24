package com.ahmaddudayef.moviesmade.core.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.core.data.Image
import com.ahmaddudayef.moviesmade.core.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.util.loadImageUrl

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShow>()
    var clickTvShowListener: (TvShow) -> Unit = {}

    fun setListTvShow(listTvShow: List<TvShow>?) {
        if (listTvShow == null) return
        val diffCallback = TvShowDiffCallback(this.listTvShow, listTvShow)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listTvShow.clear()
        this.listTvShow.addAll(listTvShow)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listTvShow[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            clickTvShowListener(data)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tvTitleFilm.text = tvShow.name
            binding.tvDescriptionFilm.text = tvShow.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShow.posterPath)
        }
    }

}