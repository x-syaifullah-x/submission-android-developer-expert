package com.android.developer.expert.core.data.source.local.base

interface ITvResultEntity : IResultEntity {
    val foreignKey: Int
    val originalName: String
    val name: String
}