package com.mycash.yajhaz.core.connectivity.connectivity_manager

import android.net.Network

interface NetworkAwareComponent {

    fun onNetworkAvailable(network: Network)

    fun onNetworkLost(network: Network)
}
