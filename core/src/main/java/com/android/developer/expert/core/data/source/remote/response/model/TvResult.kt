package com.android.developer.expert.core.data.source.remote.response.model

import com.android.developer.expert.core.data.source.remote.response.base.ITvResult
import com.google.gson.annotations.SerializedName

data class TvResult(
    @SerializedName("original_name") override val originalName: String,
    @SerializedName("name") override val name: String,

    @SerializedName("poster_path") override val posterPath: String?,
    @SerializedName("id") override val id: Int,
    @SerializedName("backdrop_path") override val backdropPath: String?,
    @SerializedName("vote_average") override val voteAverage: Float,
    @SerializedName("overview") override val overview: String,
    @SerializedName("original_language") override val originalLanguage: String,
) : ITvResult