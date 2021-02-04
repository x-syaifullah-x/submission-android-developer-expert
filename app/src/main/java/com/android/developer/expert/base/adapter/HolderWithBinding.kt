package com.android.developer.expert.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class HolderWithBinding<T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)