package com.ahmaddudayef.moviesmade.presentation.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmaddudayef.moviesmade.data.Image
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.databinding.ItemMovieBinding
import com.ahmaddudayef.moviesmade.presentation.detailtvshow.DetailTvShowActivity
import com.ahmaddudayef.moviesmade.util.loadImageUrl

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var listTvShow: List<TvShow> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
        holder.itemView.setOnClickListener {
            val data = listTvShow[position]
            val context = it.context
            val intent = Intent(context, DetailTvShowActivity::class.java)
            intent.putExtra(DetailTvShowActivity.DETAIL_TVSHOW, data)
            context.startActivity(intent)
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