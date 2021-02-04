package com.android.developer.expert.core.data.source.local.base

interface IMovieResultEntity : IResultEntity {
    val foreignKey: Int
    val releaseDate: String
    val video: Boolean
    val adult: Boolean
    val originalTitle: String?
    val title: String?
}