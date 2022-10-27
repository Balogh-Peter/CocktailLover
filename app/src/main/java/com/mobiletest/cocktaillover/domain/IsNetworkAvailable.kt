package com.mobiletest.cocktaillover.domain

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat.getSystemService
import com.mobiletest.cocktaillover.application.MyApplication.Companion.appContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsNetworkAvailable @Inject constructor() {

    private var isNetworkAvailable = false

    init {
        listenForNetworkUpdates()
    }

    operator fun invoke(): Boolean {
        return isNetworkAvailable
    }

    private fun listenForNetworkUpdates() {
        val networkRequest = getNetworkRequest()
        val networkCallback = getNetworkCallback()

        val connectivityManager =
            getSystemService(appContext, ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private fun getNetworkRequest() = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private fun getNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }
    }

}