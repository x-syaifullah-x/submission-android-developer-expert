package com.android.developer.expert.presentation.tv

import androidx.navigation.fragment.findNavController
import com.android.developer.expert.R
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.presentation.detail.DetailActivity
import com.android.developer.expert.presentation.movie.MovieFragment

open class TvFragment : MovieFragment() {

    override fun submitData() {
        viewModel.discoverTv.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }

    override fun handleLoadStatListenerFirstLoadErrorDataEmpty() {
        if (isRetry) adapter.retry() else findNavController().navigate(R.id.fragment_tv_to_fragment_error)
    }

    override fun onItemClick(itemModel: ItemModel) {
        openActivity<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Tv(itemModel.id)) }
    }
}