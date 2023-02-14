package com.cajusoftware.fipe.utils

import androidx.navigation.NavController
import androidx.navigation.NavDestination

object NavUtils {
    var destinationListener: ((NavController, NavDestination) -> Unit)? = null

    val navControllerDestinationChangeListener =
        NavController.OnDestinationChangedListener { controller, destination, _ ->
            destinationListener?.invoke(controller, destination)
        }
}