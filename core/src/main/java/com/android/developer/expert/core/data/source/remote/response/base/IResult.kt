package com.android.developer.expert.core.data.source.remote.response.base

interface IResult {
    val posterPath: String?
    val id: Int
    val backdropPath: String?
    val voteAverage: Float
    val overview: String
    val originalLanguage: String
}