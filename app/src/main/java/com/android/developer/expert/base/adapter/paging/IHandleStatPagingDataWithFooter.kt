package com.android.developer.expert.base.adapter.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

interface IHandleStatPagingDataWithFooter {
    val adapter: BasePagingDataAdapter<*, *>

    val footerAdapter: FooterAdapter

    fun handleLoadStatListener(combinedLoadStates: CombinedLoadStates) {
        if (
            combinedLoadStates.source.refresh is LoadState.NotLoading &&
            combinedLoadStates.source.prepend.endOfPaginationReached &&
            combinedLoadStates.source.append.endOfPaginationReached &&
            combinedLoadStates.mediator == null || combinedLoadStates.mediator?.append?.endOfPaginationReached == true &&
            adapter.snapshot().isEmpty() &&
            combinedLoadStates.mediator?.prepend?.endOfPaginationReached == true &&
            combinedLoadStates.mediator?.append?.endOfPaginationReached == true
        ) handleLoadStatListenerDataEmpty()

        if (combinedLoadStates.mediator?.append?.endOfPaginationReached == true && adapter.snapshot().isNotEmpty())
            handleLoadStatListenerEndPage()

        if (combinedLoadStates.source.refresh is LoadState.NotLoading && combinedLoadStates.mediator?.refresh is LoadState.Error && adapter.snapshot().isEmpty())
            handleLoadStatListenerFirstLoadErrorDataEmpty()

        if (combinedLoadStates.refresh is LoadState.Loading)
            handleLoadListenerFirstLoading()

        if (combinedLoadStates.refresh is LoadState.Error && adapter.snapshot().isNotEmpty())
            handleLoadStatListenerFirstLoadErrorDataNotEmpty(combinedLoadStates)
    }

    fun handleLoadStatListenerDataEmpty() = Unit
    fun handleLoadStatListenerEndPage() = Unit
    fun handleLoadStatListenerFirstLoadErrorDataEmpty() = Unit
    fun handleLoadStatListenerFirstLoadErrorDataNotEmpty(combinedLoadStates: CombinedLoadStates) {
        footerAdapter.loadState = combinedLoadStates.refresh
    }

    fun handleLoadListenerFirstLoading() {
        footerAdapter.loadState = LoadState.Loading
    }
}