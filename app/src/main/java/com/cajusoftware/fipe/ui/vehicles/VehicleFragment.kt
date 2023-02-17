package com.cajusoftware.fipe.ui.vehicles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cajusoftware.fipe.databinding.FragmentVehicleBinding

class VehicleFragment : Fragment() {

    private val args: VehicleFragmentArgs by navArgs()

    private lateinit var navController: NavController

    private lateinit var binding: FragmentVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleBinding.inflate(inflater)

        Log.d("VehicleFragment", args.brandNumber)
        return binding.root
    }
}