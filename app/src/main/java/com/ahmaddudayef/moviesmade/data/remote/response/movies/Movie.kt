package com.ahmaddudayef.moviesmade.data.remote.response.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie(

    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("vote_count")
    val voteCount: Int
) : Parcelable {
    constructor() : this(0, "", "", "", false, "", "", "", "", 0.0, 0.0, false, 0)
}