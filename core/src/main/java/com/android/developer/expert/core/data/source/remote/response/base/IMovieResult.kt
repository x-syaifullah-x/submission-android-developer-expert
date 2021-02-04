package com.android.developer.expert.core.data.source.remote.response.base

interface IMovieResult : IResult {
    val video: Boolean
    val adult: Boolean
    val originalTitle: String?
    val title: String?
}