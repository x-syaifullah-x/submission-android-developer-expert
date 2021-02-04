package com.android.developer.expert.core.data.source.remote.response.model

import com.google.gson.annotations.SerializedName

data class Created(
    @SerializedName("id") val id: Int,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("profile_path") val profilePath: String?
)