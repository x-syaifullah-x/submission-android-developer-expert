package com.android.developer.expert.extension

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <reified T : Activity> Fragment.openActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(requireActivity(), T::class.java)
    block(intent)
    startActivity(intent)
}

inline fun <reified T : Activity> Fragment.openActivityAndFinis(block: Intent.() -> Unit = {}) {
    val intent = Intent(requireActivity(), T::class.java)
    block(intent)
    startActivity(intent)
    requireActivity().finish()
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)