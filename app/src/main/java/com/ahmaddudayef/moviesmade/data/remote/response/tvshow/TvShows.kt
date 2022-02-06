package com.ahmaddudayef.moviesmade.data.remote.response.tvshow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShows(

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val results: List<TvShow>,

    @SerializedName("total_results")
    val totalResults: Int
) : Parcelable