package com.android.developer.expert.presentation.movie

import android.content.Context
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.developer.expert.App
import com.android.developer.expert.R
import com.android.developer.expert.base.PagingDataFragment
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.di.multibinding.ViewModelFactory
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.presentation.MainViewModel
import com.android.developer.expert.presentation.adapter.ItemAdapter
import com.android.developer.expert.presentation.detail.DetailActivity
import javax.inject.Inject

open class MovieFragment : PagingDataFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel by navGraphViewModels<MainViewModel>(R.id.nav_graph_main) { viewModelFactory }

    final override val adapter = ItemAdapter { _, itemModel -> onItemClick(itemModel) }

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun submitData() {
        viewModel.discoverMovie.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }

    override fun handleLoadStatListenerFirstLoadErrorDataEmpty() {
        if (isRetry) adapter.retry() else findNavController().navigate(R.id.fragment_movie_to_fragment_error)
    }

    open fun onItemClick(itemModel: ItemModel) {
        openActivity<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Movie(itemModel.id)) }
    }

    override fun getStatScroll(): HashMap<String, Int> = viewModel.statScroll

    override fun saveStatScroll(scrollPosition: Int, key: String) {
        viewModel.statScroll[key] = scrollPosition
    }
}