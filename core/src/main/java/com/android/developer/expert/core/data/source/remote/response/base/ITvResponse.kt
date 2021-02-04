package com.android.developer.expert.core.data.source.remote.response.base

import com.android.developer.expert.core.data.source.remote.response.model.*

interface ITvResponse : IResponse {
    override val backdropPath: String?
    override val genres: List<Genres>
    override val homepage: String?
    override val id: Int
    override val originalLanguage: String
    override val overview: String
    override val popularity: Double
    override val posterPath: String?
    override val productionCompanies: List<ProductionCompanies>
    override val productionCountries: List<ProductionCountries>
    override val status: String
    override val tagLine: String?
    override val voteAverage: Float
    override val voteCount: Int

    val createdBy: List<Created>
    val episodeRunTime: List<Int>
    val firstAirDate: String
    val inProduction: Boolean
    val languages: List<String>
    val lastAirDate: String
    val lastEpisodeToAir: LastEpisodeToAir
    val name: String
    val nextEpisodeToAir: NextEpisodeToAir?
    val networks: List<Networks>
    val numberOfEpisodes: Int
    val numberOfSeasons: Int
    val originCountry: List<String>
    val originalName: String
    val seasons: List<Seasons>
    val type: String
}