package com.cajusoftware.fipe.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.cajusoftware.fipe.data.network.ConnectivityStatus
import com.cajusoftware.fipe.data.network.ConnectivityStatus.OFFLINE
import com.cajusoftware.fipe.data.network.ConnectivityStatus.ONLINE
import com.cajusoftware.fipe.data.network.NoConnectivityException
import kotlinx.coroutines.CoroutineExceptionHandler

object NetworkUtils {
    var connectionErrorHandle: ((ConnectivityStatus) -> Unit)? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is NoConnectivityException -> connectionErrorHandle?.invoke(OFFLINE)
            else -> Log.e("Network-Errors", throwable.message, throwable)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun networkConnectivity(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.e("networkConnectivity", "The default network is now: $network")
                connectionErrorHandle?.invoke(ONLINE)
            }

            override fun onLost(network: Network) {
                connectionErrorHandle?.invoke(OFFLINE)
                Log.e(
                    "networkConnectivity",
                    "The application no longer has a default network. The last default network was $network"
                )
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                Log.e(
                    "networkConnectivity",
                    "The default network changed capabilities: $networkCapabilities"
                )
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                Log.e(
                    "networkConnectivity",
                    "The default network changed link properties: $linkProperties"
                )
            }
        })
    }
}