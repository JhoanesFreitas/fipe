package com.cajusoftware.fipe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cajusoftware.fipe.data.network.ConnectivityStatus
import com.cajusoftware.fipe.utils.NavUtils.destinationListener
import com.cajusoftware.fipe.utils.NetworkUtils.connectionErrorHandle

class MainViewModel : ViewModel() {

    private val _isHomeFragment = MutableLiveData(true)
    val isHomeFragment: LiveData<Boolean>
        get() = _isHomeFragment

    private val _isNoConnection: MutableLiveData<ConnectivityStatus> = MutableLiveData()
    val isNoConnection: LiveData<ConnectivityStatus>
        get() = _isNoConnection

    init {
        connectionErrorHandle = { isConnectionError ->
            _isNoConnection.postValue(isConnectionError)
        }
        destinationListener = { controller, destination ->
            _isHomeFragment.value = destination.id == controller.graph.startDestinationId
        }
    }
}