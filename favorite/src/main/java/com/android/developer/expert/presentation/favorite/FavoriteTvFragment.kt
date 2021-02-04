package com.android.developer.expert.presentation.favorite

import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.presentation.detail.DetailActivity

class FavoriteTvFragment : FavoriteMovieFragment() {

    override fun submitData() {
        viewModel.getFavoriteTv.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }

    override fun onItemClick(itemModel: ItemModel) {
        openActivity<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Tv(itemModel.id)) }
    }
}