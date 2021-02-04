package com.android.developer.expert.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.core.domain.usecase.Interactor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {

    private val sharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(1)

    val movie by lazy {
        sharedFlow.asSharedFlow().flatMapLatest { interactor.getMovie(it).distinctUntilChanged() }.asLiveData()
    }

    val tv by lazy {
        sharedFlow.asSharedFlow().flatMapLatest { interactor.getTv(it).distinctUntilChanged() }.asLiveData()
    }

    fun sendId(id: Int) = viewModelScope.launch { sharedFlow.emit(id) }

    fun setFavorite(type: Type<*>, isFavorite: Boolean) = viewModelScope.launch {
        interactor.setFavorite(type, isFavorite)
    }
}