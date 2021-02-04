package com.android.developer.expert.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.developer.expert.core.domain.usecase.Interactor
import javax.inject.Inject

open class MainViewModel @Inject constructor(interactor: Interactor) : ViewModel() {

    val discoverMovie by lazy {
        interactor.getDiscoverMovie().cachedIn(viewModelScope).asLiveData()
    }

    val discoverTv by lazy {
        interactor.getDiscoverTv().cachedIn(viewModelScope).asLiveData()
    }

    val statScroll: HashMap<String, Int> = hashMapOf()
}