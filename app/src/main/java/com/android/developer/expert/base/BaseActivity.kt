package com.android.developer.expert.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<ActivityBinding : ViewBinding> : AppCompatActivity() {

    abstract val binding: ActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        try {
            val navHostFragment = supportFragmentManager.fragments.first() as NavHostFragment
            val fragment = navHostFragment.childFragmentManager.fragments
            fragment.forEach { it.onOptionsItemSelected(item) }
        } finally {
            return super.onOptionsItemSelected(item)
        }
    }
}