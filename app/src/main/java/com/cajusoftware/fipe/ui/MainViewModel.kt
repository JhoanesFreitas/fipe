package com.cajusoftware.fipe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cajusoftware.fipe.utils.NavUtils.destinationListener

class MainViewModel : ViewModel() {

    private val _isHomeFragment = MutableLiveData(true)
    val isHomeFragment: LiveData<Boolean>
        get() = _isHomeFragment

    init {
        destinationListener = { controller, destination ->
            _isHomeFragment.value = destination.id == controller.graph.startDestinationId
        }
    }
}