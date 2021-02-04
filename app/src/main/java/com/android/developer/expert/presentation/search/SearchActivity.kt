package com.android.developer.expert.presentation.search

import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT
import com.android.developer.expert.App
import com.android.developer.expert.R
import com.android.developer.expert.base.BaseActivity
import com.android.developer.expert.core.domain.model.Resource
import com.android.developer.expert.core.domain.model.SearchModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.core.domain.repository.SearchRepository.Companion.MEDIA_TYPE_TV
import com.android.developer.expert.databinding.ActivitySearchBinding
import com.android.developer.expert.di.multibinding.ViewModelFactory
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.extension.viewBinding
import com.android.developer.expert.presentation.adapter.ItemSearchAdapter
import com.android.developer.expert.presentation.detail.DetailActivity
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val searchAdapter = ItemSearchAdapter { _, model -> onItemClick(model) }

    override val binding: ActivitySearchBinding by viewBinding(ActivitySearchBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.content.searchField.addTextChangedListener { viewModel.send(it.toString()) }

        binding.content.rvSearch.apply {
            setHasFixedSize(true)
            searchAdapter.stateRestorationPolicy = PREVENT
            adapter = searchAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        viewModel.search.observe(this) {
            when (it) {
                is Resource.Success -> with(it.data) {
                    submit(
                        this,
                        if (isEmpty()) getString(R.string.empty) else getString(R.string.success)
                    )
                }
                is Resource.Empty -> submit(null, getString(R.string.empty))
                is Resource.Loading -> submit(null, getString(R.string.loading), true)
                is Resource.Error -> submit(it.data, getString(R.string.error))
            }
        }
    }

    private fun submit(data: List<SearchModel>?, msg: String? = null, isLoad: Boolean = false) {
        binding.content.textMessage.text = msg
        val view = binding.content
        view.progress.isVisible = isLoad
        if (!isLoad) {
            val icon = if (data.isNullOrEmpty()) R.drawable.ic_filed else R.drawable.ic_check
            view.searchDone.setImageDrawable(ContextCompat.getDrawable(this, icon))
            searchAdapter.submitData(lifecycle, PagingData.from(data ?: listOf()))
            view.rvSearch.scrollToPosition(0)
            view.group.isVisible = data.isNullOrEmpty()
        }
    }

    private fun onItemClick(model: SearchModel) {
        openActivity<DetailActivity> {
            val id = model.id ?: return
            val type = if (model.mediaType == MEDIA_TYPE_TV) Type.Tv(id) else Type.Movie(id)
            putExtra(Type.DATA_EXTRA, type)
        }
    }
}