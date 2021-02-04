package com.android.developer.expert.base.adapter.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.developer.expert.base.adapter.HolderWithBinding
import com.android.developer.expert.core.domain.model.base.IModel

abstract class BasePagingDataAdapter<ItemBinding : ViewDataBinding, Model : IModel<*>>(
    @LayoutRes private val itemLayout: Int
) : PagingDataAdapter<Model, HolderWithBinding<ItemBinding>>(Diff<Model>()) {

    @SuppressLint("DiffUtilEquals")
    private class Diff<Model : IModel<*>> : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(
            oldItem: Model, newItem: Model
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Model, newItem: Model
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HolderWithBinding<ItemBinding> =
        HolderWithBinding(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemLayout, parent, false)
        )
}