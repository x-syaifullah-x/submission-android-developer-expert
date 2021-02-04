package com.android.developer.expert.core.data.source.remote.response

import com.android.developer.expert.core.data.source.remote.response.base.IMovieResponse
import com.android.developer.expert.core.data.source.remote.response.model.*
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("backdrop_path") override val backdropPath: String?,
    @SerializedName("genres") override val genres: List<Genres>,
    @SerializedName("homepage") override val homepage: String?,
    @SerializedName("id") override val id: Int,
    @SerializedName("original_language") override val originalLanguage: String,
    @SerializedName("overview") override val overview: String,
    @SerializedName("popularity") override val popularity: Double,
    @SerializedName("poster_path") override val posterPath: String?,
    @SerializedName("production_companies") override val productionCompanies: List<ProductionCompanies>,
    @SerializedName("production_countries") override val productionCountries: List<ProductionCountries>,
    @SerializedName("status") override val status: String,
    @SerializedName("tagline") override val tagLine: String?,
    @SerializedName("vote_average") override val voteAverage: Float,
    @SerializedName("vote_count") override val voteCount: Int,
    @SerializedName("adult") override val adult: Boolean,
    @SerializedName("belongs_to_collection") override val belongsToCollection: BelongsToCollection?,
    @SerializedName("budget") override val budget: Int,
    @SerializedName("imDbId") override val imDbId: String?,
    @SerializedName("original_title") override val originalTitle: String,
    @SerializedName("release_date") override val releaseDate: String?,
    @SerializedName("revenue") override val revenue: Int,
    @SerializedName("runtime") override val runtime: Int?,
    @SerializedName("spoken_languages") override val spokenLanguages: List<SpokenLanguages>,
    @SerializedName("title") override val title: String,
    @SerializedName("video") override val video: Boolean,
) : IMovieResponse