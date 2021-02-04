package com.android.developer.expert.core.data.source.local.base

interface IPageEmbed<Result> {
    fun page(): Int
    fun totalPages(): Int
    fun totalResult(): Int
    fun results(): List<Result>
}