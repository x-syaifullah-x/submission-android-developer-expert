package com.android.developer.expert.core.data.source.remote.response

import com.android.developer.expert.core.data.source.remote.response.base.ITvResponse
import com.android.developer.expert.core.data.source.remote.response.model.*
import com.google.gson.annotations.SerializedName

data class TvResponse(
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
    @SerializedName("created_by") override val createdBy: List<Created>,
    @SerializedName("episode_run_time") override val episodeRunTime: List<Int>,
    @SerializedName("first_air_date") override val firstAirDate: String,
    @SerializedName("in_production") override val inProduction: Boolean,
    @SerializedName("languages") override val languages: List<String>,
    @SerializedName("last_air_date") override val lastAirDate: String,
    @SerializedName("last_episode_to_air") override val lastEpisodeToAir: LastEpisodeToAir,
    @SerializedName("name") override val name: String,
    @SerializedName("next_episode_to_air") override val nextEpisodeToAir: NextEpisodeToAir?,
    @SerializedName("networks") override val networks: List<Networks>,
    @SerializedName("number_of_episodes") override val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") override val numberOfSeasons: Int,
    @SerializedName("origin_country") override val originCountry: List<String>,
    @SerializedName("original_name") override val originalName: String,
    @SerializedName("seasons") override val seasons: List<Seasons>,
    @SerializedName("type") override val type: String,
) : ITvResponse