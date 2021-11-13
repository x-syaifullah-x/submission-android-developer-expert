package com.android.developer.expert.core.data.source.remote.response.model

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)