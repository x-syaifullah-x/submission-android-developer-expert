package com.android.developer.expert.core.data.source.remote.response.base

interface IResult {
    val popularity: Double
    val voteCount: Int
    val posterPath: String?
    val id: Int
    val backdropPath: String?
    val originalLanguage: String
    val genreIds: List<Int>
    val voteAverage: Float
    val overview: String
    val releaseDate: String?
}