package com.android.developer.expert.core.data.source.remote.response.model

import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.base.ITvResponse
import com.google.gson.annotations.SerializedName

data class TvWithTvRecommendation(
    @SerializedName("backdrop_path") override val backdropPath: String?,
    @SerializedName("genres") override val genres: List<Genres>,
    @SerializedName("id") override val id: Int,
    @SerializedName("original_language") override val originalLanguage: String,
    @SerializedName("overview") override val overview: String,
    @SerializedName("poster_path") override val posterPath: String?,
    @SerializedName("vote_average") override val voteAverage: Float,
    @SerializedName("first_air_date") override val firstAirDate: String,
    @SerializedName("last_air_date") override val lastAirDate: String,
    @SerializedName("name") override val name: String,
    @SerializedName("original_name") override val originalName: String,
    @SerializedName("recommendation") val recommendations: List<PageResponse<TvResult>>
) : ITvResponse