package com.android.developer.expert.core.data.source.remote.response.model

import com.google.gson.annotations.SerializedName

data class ProductionCountries(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String
)