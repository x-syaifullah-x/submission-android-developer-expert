package com.android.developer.expert.core.data.source.remote.response.base

import com.android.developer.expert.core.data.source.remote.response.model.Genres

interface IResponse {
    val id: Int
    val backdropPath: String?
    val originalLanguage: String
    val overview: String
    val posterPath: String?
    val voteAverage: Float
    val genres: List<Genres>
}