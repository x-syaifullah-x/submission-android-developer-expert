package com.android.developer.expert.presentation.adapter

import android.view.View
import com.android.developer.expert.R
import com.android.developer.expert.base.adapter.HolderWithBinding
import com.android.developer.expert.base.adapter.paging.BasePagingDataAdapter
import com.android.developer.expert.core.domain.model.SearchModel
import com.android.developer.expert.databinding.ItemSearchBinding

open class ItemSearchAdapter(
    private val onItemClick: (View, SearchModel) -> Unit = { _, _ -> }
) : BasePagingDataAdapter<ItemSearchBinding, SearchModel>(R.layout.item_search) {

    override fun onBindViewHolder(holder: HolderWithBinding<ItemSearchBinding>, position: Int) {
        val binding = holder.binding
        val data = getItem(position) ?: return
        binding.data = data
        binding.root.setOnClickListener { onItemClick(it, data) }
    }
}