package com.android.developer.expert.core.data.source.local.base

interface IDetail {
    fun backdropPath(): String?
    fun homepage(): String?
    fun id(): Int
    fun originalLanguage(): String?
    fun overview(): String
    fun popularity(): Double
    fun posterPath(): String?
    fun status(): String
    fun tagLine(): String?
    fun voteAverage(): Float
    fun voteCount(): Int
}