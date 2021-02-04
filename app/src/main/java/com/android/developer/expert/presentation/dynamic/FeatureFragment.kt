package com.android.developer.expert.presentation.dynamic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.developer.expert.R
import com.android.developer.expert.databinding.FragmentFeatureBinding
import com.android.developer.expert.extension.viewBinding
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

class FeatureFragment : Fragment(R.layout.fragment_feature) {

    val binding by viewBinding { FragmentFeatureBinding.bind(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            installModule()
        } catch (e: Exception) {
            Log.i("TAG", "onActivityCreated: ${e.message}")
            binding.tvMessage.text = e.localizedMessage
        }
    }

    private fun installModule() {
        val moduleName = "favorite"
        val splitInstallManager = SplitInstallManagerFactory.create(requireContext())
        if (splitInstallManager.installedModules.contains(moduleName)) {
            findNavController().navigate(R.id.action_to_fragment_favorite)
        } else {
            val request = SplitInstallRequest.newBuilder().addModule(moduleName).build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    findNavController().navigate(R.id.action_to_fragment_favorite)
                }
                .addOnFailureListener {
                    binding.tvMessage.text = it.localizedMessage
                }
        }
    }
}