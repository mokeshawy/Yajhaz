package com.mycash.yajhaz.features.activities

import android.net.Network
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.connectivity.connectivity_manager.NetworkAwareComponent
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.error.YajhazErrorHandler
import com.mycash.yajhaz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetworkAwareComponent, YajhazErrorHandler {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        hideSystemBars(binding.root)

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemBars(binding.root)
        }
    }

    private fun hideSystemBars(root: View) {
        val windowInsetsController = WindowInsetsControllerCompat(window, root)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }


    fun getCurrentFragment() = navHostFragment.childFragmentManager.fragments.firstOrNull()

    override fun onNetworkAvailable(network: Network) {
        (getCurrentFragment() as? NetworkAwareComponent)?.onNetworkAvailable(network)
    }

    override fun onNetworkLost(network: Network) {
        (getCurrentFragment() as? NetworkAwareComponent)?.onNetworkLost(network)
    }

    override fun handleError(error: YajhazError, callback: YajhazError.() -> Unit) {
        error.logError()
        callback(error)
    }

}