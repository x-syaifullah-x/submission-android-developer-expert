package com.android.developer.expert.presentation

import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.developer.expert.R
import com.android.developer.expert.R.id.nav_host_main
import com.android.developer.expert.base.BaseActivity
import com.android.developer.expert.databinding.ActivityMainBinding
import com.android.developer.expert.extension.disableDisplayShowAndHomeAsUp
import com.android.developer.expert.extension.openActivity
import com.android.developer.expert.extension.viewBinding
import com.android.developer.expert.presentation.search.SearchActivity
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    override val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(nav_host_main) as NavHostFragment
        navController = navHostFragment.navController

        setupWithNavController(binding.bottomNavigationView, navController)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.fragment_movie,
            R.id.fragment_tv,
            R.id.fragment_feature
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            addOnDestinationChangedListener(destination)
        }

        binding.fabSearch.setOnClickListener { openActivity<SearchActivity>() }
    }

    override fun onSupportNavigateUp() = super.onSupportNavigateUp() || navController.navigateUp()

    private fun addOnDestinationChangedListener(destination: NavDestination) {
        val isFragmentError = destination.id == R.id.fragment_error
        binding.fabSearch.isVisible = !isFragmentError
        setScrollFlags(isFragmentError)
        if (isFragmentError) showBottomNavigation()

        disableDisplayShowAndHomeAsUp(
            destination,
            R.id.fragment_error,
            R.id.fragment_favorite
        )
    }

    private fun showBottomNavigation() {
        val bottomNav = binding.bottomNavigationView
        val layoutParam = bottomNav.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParam.behavior as HideBottomViewOnScrollBehavior
        behavior.slideUp(bottomNav)
    }

    private fun setScrollFlags(isScrollFlags: Boolean) {
        val toolbar = binding.toolbar.layoutParams as LayoutParams
        toolbar.scrollFlags =
            if (isScrollFlags) SCROLL_FLAG_NO_SCROLL else SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
    }
}