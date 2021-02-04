package com.android.developer.expert.presentation.detail

import android.os.Bundle
import androidx.paging.PagingData.Companion.from
import com.android.developer.expert.R
import com.android.developer.expert.core.domain.model.DetailTvModel
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.databinding.FragmentDetailTvBinding
import com.android.developer.expert.extension.openActivityAndFinis
import com.android.developer.expert.extension.viewBinding

class DetailTvFragment : DetailBaseFragment<DetailTvModel>(R.layout.fragment_detail_tv) {

    private val binding by viewBinding { FragmentDetailTvBinding.bind(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.movieRecyclerView.adapter = adapter
        viewModel.tv.observe(viewLifecycleOwner) { submitData(it) }
    }

    override fun onResultSubmitData(data: DetailTvModel) {
        binding.data = data
        adapter.submitData(lifecycle, from(data.recommendation))
    }

    override fun onClickItemRecommendation(itemModel: ItemModel) {
        openActivityAndFinis<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Tv(itemModel.id)) }
    }
}