package com.ahmaddudayef.moviesmade.data.remote.response.tvshow

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvshow")
@Parcelize
data class TvShow(

    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("name")
    val name: String,

    @SerializedName("vote_count")
    val voteCount: Int
) : Parcelable