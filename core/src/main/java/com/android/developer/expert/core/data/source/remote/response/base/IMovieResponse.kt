package com.android.developer.expert.core.data.source.remote.response.base

import com.android.developer.expert.core.data.source.remote.response.model.Genres

interface IMovieResponse : IResponse {
    override val backdropPath: String?
    override val genres: List<Genres>
    override val id: Int
    override val originalLanguage: String
    override val overview: String
    override val posterPath: String?
    override val voteAverage: Float

    val originalTitle: String
    val releaseDate: String?
    val title: String
}