package com.android.developer.expert.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.developer.expert.App
import com.android.developer.expert.R
import com.android.developer.expert.base.BaseActivity
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.databinding.ActivityDetailBinding
import com.android.developer.expert.di.multibinding.ViewModelFactory
import com.android.developer.expert.extension.viewBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import javax.inject.Inject
import kotlin.properties.Delegates

class DetailActivity : BaseActivity<ActivityDetailBinding>(), OnOffsetChangedListener, OnRefreshListener {

    override val binding by viewBinding(ActivityDetailBinding::inflate)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }
    private var id: Int by Delegates.notNull()
    private var type: Type<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        (application as App).appComponent.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        type = intent.getParcelableExtra(Type.DATA_EXTRA)
        id = when (type) {
            is Type.Movie -> setFragment(DetailMovieFragment(), (type as Type.Movie).id)
            is Type.Tv -> setFragment(DetailTvFragment(), (type as Type.Tv).id)
            else -> throw IllegalArgumentException("${this::class.java.name} require data ${Type::class.java.name}")
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        showBottomSheet(false)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val ct = binding.collapsingToolbar
        binding.swipeRefresh.isEnabled = (ct.height + verticalOffset) >= (5 * ViewCompat.getMinimumHeight(ct))
    }

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = false
        viewModel.apply { sendId(id) }
    }

    override fun onBackPressed() {
        if (bottomSheetBehavior.state == STATE_EXPANDED) showBottomSheet(false) else super.onBackPressed()
    }

    private fun setFragment(fragment: Fragment, id: Int): Int {
        supportFragmentManager.commit { replace(R.id.fragment, fragment) }
            .apply { viewModel.sendId(id) }
        binding.appBar.addOnOffsetChangedListener(this)
        binding.swipeRefresh.setOnRefreshListener(this)
        return id
    }

    private fun showBottomSheet(isShow: Boolean) {
        binding.fab.isVisible = !isShow
        bottomSheetBehavior.state = if (isShow) STATE_EXPANDED else STATE_HIDDEN
    }

    fun setFabAddDelete(isFavorite: Boolean) {
        showBottomSheet(false)
        binding.fab.setOnClickListener {
            binding.confirm.text = getString(R.string.confirm, if (isFavorite) "Add" else "Delete")
            showBottomSheet(true)
            binding.btnYes.setOnClickListener {
                showBottomSheet(false)
                viewModel.setFavorite(type!!, isFavorite)
            }
            binding.btnNo.setOnClickListener { showBottomSheet(false) }
        }
    }
}