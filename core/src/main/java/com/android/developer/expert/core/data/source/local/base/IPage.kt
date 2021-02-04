package com.android.developer.expert.core.data.source.local.base

interface IPage {
    val page: Int
    val totalPages: Int
    val totalResults: Int
}