package com.android.developer.expert.extension

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.viewbinding.ViewBinding

inline fun <reified A : Activity> Activity.openActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, A::class.java)
    block(intent)
    startActivity(intent)
}

fun AppCompatActivity.disableDisplayShowAndHomeAsUp(
    navDestination: NavDestination,
    @IdRes vararg idFragment: Int
) {
    if (idFragment.contains(navDestination.id)) {
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE)
{ bindingInflater.invoke(layoutInflater) }
