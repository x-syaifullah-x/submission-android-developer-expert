package com.android.developer.expert.core.data.source.local.base

interface IResultEntity : IEntity<Int> {
    val id: Int
    val posterPath: String?
    val backdropPath: String?
    val voteAverage: Float
    val overview: String?
    val originalLanguage: String
}