package com.android.developer.expert.core.data.source.local.base

interface IDetail {
    fun backdropPath(): String?
    fun id(): Int
    fun originalLanguage(): String?
    fun overview(): String
    fun posterPath(): String?
    fun voteAverage(): Float
}