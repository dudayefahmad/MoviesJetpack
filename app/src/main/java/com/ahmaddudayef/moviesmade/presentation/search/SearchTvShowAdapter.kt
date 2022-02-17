package com.ahmaddudayef.moviesmade.presentation.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.Type
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.presentation.detail.DetailActivity
import com.ahmaddudayef.moviesmade.util.loadImageUrl

class SearchTvShowAdapter : RecyclerView.Adapter<SearchTvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShow>()

    fun setListTvShow(listTvShow: List<TvShow>) {
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

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
        holder.itemView.setOnClickListener {
            val data = listTvShow[position]
            val context = it.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, Type.TYPE_TVSHOW)
            intent.putExtra(DetailActivity.EXTRA_FROM_SEARCH, true)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tvTitleFilm.text = tvShow.name
            binding.tvDescriptionFilm.text = tvShow.overview
            binding.imgMovie.loadImageUrl(Image.IMAGE_URL + Image.SIZE + tvShow.posterPath)
        }
    }
}