package com.android.developer.expert.core.data.source.remote.response.base

import com.android.developer.expert.core.data.source.remote.response.model.*

interface IMovieResponse : IResponse {
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

    val adult: Boolean
    val belongsToCollection: BelongsToCollection?
    val budget: Int
    val imDbId: String?
    val originalTitle: String
    val releaseDate: String?
    val revenue: Int
    val runtime: Int?
    val spokenLanguages: List<SpokenLanguages>
    val title: String
    val video: Boolean
}