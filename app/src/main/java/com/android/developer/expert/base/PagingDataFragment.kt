package com.android.developer.expert.base

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.developer.expert.R
import com.android.developer.expert.base.adapter.paging.FooterAdapter
import com.android.developer.expert.base.adapter.paging.IHandleStatPagingDataWithFooter
import com.android.developer.expert.databinding.FragmentPagingDataBinding
import com.android.developer.expert.extension.viewBinding

abstract class PagingDataFragment : Fragment(
    R.layout.fragment_paging_data
), IHandleStatPagingDataWithFooter {

    companion object {
        const val IS_ERROR = "IS_ERROR"
        const val ONE_ROW = 1
        const val FOUR_ROW = 4
    }

    private val binding by viewBinding { FragmentPagingDataBinding.bind(it) }

    private val girdLayoutManager by lazy { binding.movieRecyclerView.layoutManager as GridLayoutManager }

    private val spanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val footerIsShow = (footerAdapter.itemCount == 1)
                return if (position >= adapter.itemCount && footerIsShow) girdLayoutManager.spanCount else ONE_ROW
            }
        }
    }

    override val footerAdapter: FooterAdapter = FooterAdapter { adapter.retry() }

    protected val isRetry: Boolean
        get() {
            val isRetry = arguments?.getBoolean(IS_ERROR) ?: false
            arguments?.putAll(bundleOf(IS_ERROR to null)) /* reset argument IS_ERROR */
            return isRetry
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE)
            girdLayoutManager.spanCount = FOUR_ROW

        binding.movieRecyclerView.apply {
            setHasFixedSize(true)
            adapter = this@PagingDataFragment.adapter.withLoadStateFooter(footerAdapter)
            girdLayoutManager.spanSizeLookup = spanSizeLookup
            scrollToPosition(getStatScroll()[keyStatScroll()] ?: 0)
        }
        submitData()

        if (enableHandleLoadStatListener()) adapter.addLoadStateListener { handleLoadStatListener(it) }
    }

    abstract fun submitData()

    private fun keyStatScroll(): String = this::class.java.name

    protected abstract fun getStatScroll(): HashMap<String, Int>

    protected abstract fun saveStatScroll(scrollPosition: Int, key: String)

    protected open fun enableHandleLoadStatListener(): Boolean = true

    override fun onPause() {
        super.onPause()
        saveStatScroll(girdLayoutManager.findFirstVisibleItemPosition(), keyStatScroll())
    }
}