package com.android.developer.expert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Type<out Int> : Parcelable {
    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
    }

    @Parcelize
    data class Movie(val id: Int) : Type<Int>()

    @Parcelize
    data class Tv(val id: Int) : Type<Int>()
}