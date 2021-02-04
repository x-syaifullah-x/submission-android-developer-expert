package com.android.developer.expert.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.developer.expert.core.domain.usecase.Interactor
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(interactor: Interactor) : ViewModel() {
    val getFavoriteMovie by lazy {
        interactor.getMovieFavorite().cachedIn(viewModelScope).asLiveData()
    }

    val getFavoriteTv by lazy {
        interactor.getTvFavorite().cachedIn(viewModelScope).asLiveData()
    }

    val statScroll: HashMap<String, Int> = hashMapOf()
}