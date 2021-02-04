package com.android.developer.expert.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.developer.expert.core.domain.usecase.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {

    private val statFlow = MutableStateFlow("")

    val search = statFlow
        .debounce(300)
        .flatMapLatest { interactor.search(it) }
        .asLiveData()

    fun send(query: String) = viewModelScope.launch { statFlow.emit(query) }
}