package com.android.developer.expert.core.data.mapper

interface IMapper<I, O> {
    fun map(input: I): O
}