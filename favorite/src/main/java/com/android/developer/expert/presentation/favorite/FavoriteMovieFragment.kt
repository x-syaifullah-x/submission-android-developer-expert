package com.android.developer.expert.presentation.favorite

import android.content.Context
import androidx.navigation.navGraphViewModels
import com.android.developer.expert.App
import com.android.developer.expert.R
import com.android.developer.expert.base.PagingDataFragment
import com.android.developer.expert.base.adapter.paging.BasePagingDataAdapter
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.di.multibinding.ViewModelFactory
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.presentation.adapter.ItemAdapter
import com.android.developer.expert.presentation.detail.DetailActivity
import com.android.developer.expert.presentation.favorite.di.component.DaggerFavoriteComponent
import javax.inject.Inject

open class FavoriteMovieFragment : PagingDataFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel by navGraphViewModels<FavoriteViewModel>(R.id.nav_graph_main) { viewModelFactory }

    override val adapter: BasePagingDataAdapter<*, ItemModel> =
        ItemAdapter { _, itemModel -> onItemClick(itemModel) }

    override fun onAttach(context: Context) {
        val dataComponent = (requireActivity().application as App).dataComponent
        DaggerFavoriteComponent.factory().create(dataComponent).inject(this)
        super.onAttach(context)
    }

    override fun submitData() {
        viewModel.getFavoriteMovie.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }

    override fun getStatScroll(): HashMap<String, Int> = viewModel.statScroll

    open fun onItemClick(itemModel: ItemModel) {
        openActivity<DetailActivity> { putExtra(Type.DATA_EXTRA, Type.Movie(itemModel.id)) }
    }

    override fun saveStatScroll(scrollPosition: Int, key: String) {
        viewModel.statScroll[key] = scrollPosition
    }
}