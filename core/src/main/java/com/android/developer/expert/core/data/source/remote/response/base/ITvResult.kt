package com.android.developer.expert.core.data.source.remote.response.base

interface ITvResult : IResult {
    val originalName: String?
    val name: String?
    val originCountry: List<String>
    val firstAirDate: String
}