package com.android.developer.expert.core.data.source.local.base

interface IResultEntity : IEntity<Int> {
    val id: Int
    val popularity: Double
    val voteCount: Int
    val posterPath: String?
    val backdropPath: String?
    val originalLanguage: String?
    val voteAverage: Float
    val overview: String?
}