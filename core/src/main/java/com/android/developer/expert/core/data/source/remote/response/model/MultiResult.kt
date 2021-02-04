package com.android.developer.expert.core.data.source.remote.response.model

import com.android.developer.expert.core.data.source.remote.response.base.IMovieResult
import com.android.developer.expert.core.data.source.remote.response.base.ITvResult
import com.google.gson.annotations.SerializedName

data class MultiResult(
    @SerializedName("video") override val video: Boolean,
    @SerializedName("adult") override val adult: Boolean,
    @SerializedName("original_title") override val originalTitle: String?,
    @SerializedName("title") override val title: String?,

    @SerializedName("popularity") override val popularity: Double,
    @SerializedName("vote_count") override val voteCount: Int,
    @SerializedName("poster_path") override val posterPath: String?,
    @SerializedName("id") override val id: Int,
    @SerializedName("backdrop_path") override val backdropPath: String?,
    @SerializedName("original_language") override val originalLanguage: String,
    @SerializedName("genre_ids") override val genreIds: List<Int>,
    @SerializedName("vote_average") override val voteAverage: Float,
    @SerializedName("overview") override val overview: String,
    @SerializedName("release_date") override val releaseDate: String?,

    @SerializedName("original_name") override val originalName: String?,
    @SerializedName("name") override val name: String?,
    @SerializedName("origin_country") override val originCountry: List<String>,
    @SerializedName("first_air_date") override val firstAirDate: String,

    @SerializedName("media_type") val mediaType: String
) : ITvResult, IMovieResult