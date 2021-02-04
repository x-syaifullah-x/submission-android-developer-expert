package com.android.developer.expert.presentation.adapter

import android.view.View
import com.android.developer.expert.R
import com.android.developer.expert.base.adapter.HolderWithBinding
import com.android.developer.expert.base.adapter.paging.BasePagingDataAdapter
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.databinding.ItemBinding

open class ItemAdapter(
    private val onItemClick: (View, ItemModel) -> Unit = { _, _ -> }
) : BasePagingDataAdapter<ItemBinding, ItemModel>(R.layout.item) {

    override fun onBindViewHolder(holder: HolderWithBinding<ItemBinding>, position: Int) {
        val binding = holder.binding
        val data = getItem(position) ?: return
        binding.data = data
        binding.root.setOnClickListener { onItemClick(it, data) }
    }
}