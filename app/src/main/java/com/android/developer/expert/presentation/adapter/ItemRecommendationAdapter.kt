package com.android.developer.expert.presentation.adapter

import android.view.View
import com.android.developer.expert.R
import com.android.developer.expert.base.adapter.HolderWithBinding
import com.android.developer.expert.base.adapter.paging.BasePagingDataAdapter
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.databinding.ItemRecommendationBinding

class ItemRecommendationAdapter(
    private val onItemClick: (View, ItemModel) -> Unit = { _, _ -> }
) : BasePagingDataAdapter<ItemRecommendationBinding, ItemModel>(R.layout.item_recommendation) {

    override fun onBindViewHolder(
        holder: HolderWithBinding<ItemRecommendationBinding>,
        position: Int
    ) {
        val binding = holder.binding
        val model = getItem(position) ?: return
        binding.data = model
        binding.root.setOnClickListener { onItemClick(it, model) }
    }
}