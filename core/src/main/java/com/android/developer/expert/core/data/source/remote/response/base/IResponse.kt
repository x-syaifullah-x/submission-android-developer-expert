package com.android.developer.expert.core.data.source.remote.response.base

import com.android.developer.expert.core.data.source.remote.response.model.Genres
import com.android.developer.expert.core.data.source.remote.response.model.ProductionCompanies
import com.android.developer.expert.core.data.source.remote.response.model.ProductionCountries

interface IResponse {
    val backdropPath: String?
    val genres: List<Genres>
    val homepage: String?
    val id: Int
    val originalLanguage: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val productionCompanies: List<ProductionCompanies>
    val productionCountries: List<ProductionCountries>
    val status: String
    val tagLine: String?
    val voteAverage: Float
    val voteCount: Int
}