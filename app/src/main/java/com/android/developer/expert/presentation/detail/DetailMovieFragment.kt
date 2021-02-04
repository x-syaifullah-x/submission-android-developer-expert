package com.android.developer.expert.presentation.detail

import android.os.Bundle
import androidx.paging.PagingData.Companion.from
import com.android.developer.expert.R
import com.android.developer.expert.core.domain.model.DetailMovieModel
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.databinding.FragmentDetailMovieBinding
import com.android.developer.expert.extension.openActivityAndFinis
import com.android.developer.expert.extension.viewBinding

class DetailMovieFragment : DetailBaseFragment<DetailMovieModel>(R.layout.fragment_detail_movie) {
    private val binding by viewBinding { FragmentDetailMovieBinding.bind(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.movieRecyclerView.adapter = adapter
        viewModel.movie.observe(viewLifecycleOwner) { submitData(it) }
    }

    override fun onResultSubmitData(data: DetailMovieModel) {
        binding.data = data
        adapter.submitData(lifecycle, from(data.recommendation))
    }

    override fun onClickItemRecommendation(itemModel: ItemModel) {
        openActivityAndFinis<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Movie(itemModel.id)) }
    }
}