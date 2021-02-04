package com.android.developer.expert.presentation.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.developer.expert.extension.viewBinding
import com.android.developer.expert.presentation.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding { FragmentFavoriteBinding.bind(it) }

    private val tab = intArrayOf(R.string.tab_1_movies, R.string.tab_2_tv)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = PagerAdapter(childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private inner class PagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvFragment()
            else -> throw IllegalArgumentException("tab $position not found")
        }

        override fun getPageTitle(position: Int) =
            requireContext().resources.getString(tab[position])

        override fun getCount(): Int = tab.size
    }
}