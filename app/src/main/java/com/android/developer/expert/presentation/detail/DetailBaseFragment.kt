package com.android.developer.expert.presentation.detail

import android.os.Bundle
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Resource
import com.android.developer.expert.core.domain.model.base.IDetailModel
import com.android.developer.expert.presentation.adapter.ItemRecommendationAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED

abstract class DetailBaseFragment<Model : IDetailModel>(@LayoutRes layout: Int) : Fragment(layout) {

    private lateinit var activity: DetailActivity

    private val activityBinding by lazy { activity.binding }

    protected val viewModel by activityViewModels<DetailViewModel>()

    protected val adapter = ItemRecommendationAdapter { _, itemModel ->
        if (activity.bottomSheetBehavior.state == STATE_EXPANDED) return@ItemRecommendationAdapter
        onClickItemRecommendation(itemModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity = requireActivity() as DetailActivity
    }

    protected fun submitData(resource: Resource<Model>) = when (resource) {
        is Resource.Loading -> validate(null)
        is Resource.Success -> validate(resource.data)
        is Resource.Empty -> makeText(requireContext(), "Not Found", LENGTH_SHORT).show()
            .apply { activityBinding.collapsingToolbar.title = "Not Found" }
        is Resource.Error -> makeText(requireContext(), resource.throwable.localizedMessage, LENGTH_SHORT).show()
            .apply { if (resource.data?.isValid() != true) activityBinding.collapsingToolbar.title = "Error" else validate(resource.data) }
    }

    private fun validate(data: Model?) {
        if (data?.isValid() == true) {
            activity.setFabAddDelete(!data.isFavorite)
            onResultSubmitData(data)
        }
    }

    protected abstract fun onResultSubmitData(data: Model)

    open fun onClickItemRecommendation(itemModel: ItemModel) = Unit
}