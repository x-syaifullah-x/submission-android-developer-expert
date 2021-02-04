package com.android.developer.expert.base.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.android.developer.expert.base.adapter.HolderWithBinding
import com.android.developer.expert.databinding.ItemFooterBinding

class FooterAdapter(
    private val callbackRetry: () -> Unit
) : LoadStateAdapter<HolderWithBinding<ItemFooterBinding>>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        HolderWithBinding(ItemFooterBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(
        holder: HolderWithBinding<ItemFooterBinding>,
        loadState: LoadState
    ) {
        holder.binding.let {
            it.progressBar.isVisible = loadState is LoadState.Loading
            it.button.isVisible = loadState is LoadState.Error
            it.button.setOnClickListener { callbackRetry() }
        }
    }
}