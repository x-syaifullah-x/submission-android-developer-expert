package com.android.developer.expert.databinding

object TheMovieDBImage {
//    private const val SIZE_W185 = "wi85"
    private const val SIZE_ORIGINAL = "original"

    fun url(size: String = SIZE_ORIGINAL, path: String?) = "https://image.tmdb.org/t/p/$size/$path"
}