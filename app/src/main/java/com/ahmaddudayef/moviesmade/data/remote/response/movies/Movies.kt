package com.ahmaddudayef.moviesmade.data.remote.response.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val results: List<Movie>,

    @SerializedName("total_results")
    val totalResults: Int
) : Parcelable