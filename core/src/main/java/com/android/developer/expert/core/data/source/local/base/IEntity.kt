package com.android.developer.expert.core.data.source.local.base

interface IEntity<T> {
    companion object {
        fun <T : Number> successInsert(result: T) = result.toInt() > 0
    }

    val primaryKey: T?
}