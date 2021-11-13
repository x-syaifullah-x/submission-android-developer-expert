package com.android.developer.expert.core.data.source.remote.response

import com.android.developer.expert.core.data.source.remote.response.base.IResult
import com.google.gson.annotations.SerializedName

data class PageResponse<Result : IResult>(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<Result>
)