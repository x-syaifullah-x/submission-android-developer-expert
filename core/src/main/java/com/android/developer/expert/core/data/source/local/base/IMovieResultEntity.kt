package com.android.developer.expert.core.data.source.local.base

interface IMovieResultEntity : IResultEntity {
    val foreignKey: Int
    val originalTitle: String?
    val title: String?
}