package com.cajusoftware.fipe.ui.vehicles

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cajusoftware.fipe.databinding.FragmentVehicleBinding
import com.cajusoftware.fipe.di.ViewModelProvider.Factory
import com.cajusoftware.fipe.ui.MainActivity

class VehicleFragment : Fragment() {

    private val args: VehicleFragmentArgs by navArgs()

    private lateinit var navController: NavController

    private lateinit var binding: FragmentVehicleBinding

    private val viewModel: VehicleViewModel by viewModels { Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleBinding.inflate(inflater)
        viewModel.fetchVehicleYears(args.brandNumber, args.modelNumber)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setOnBackPressedListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchVehicleLastYear(
            args.brandNumber,
            args.modelNumber,
            args.brandName,
            args.modelName
        )
        setFullscreenClickListener()
    }

    private fun setFullscreenClickListener() {
        binding.chartView.apply {
            fullscreenClickListener = {
                activity?.requestedOrientation =
                    if (activity?.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    else
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }
    }

    private fun setOnBackPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBackPressed()
        }
        (requireActivity() as? MainActivity)?.onNavigationClickListener { onBackPressed() }
    }

    private fun onBackPressed() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        navController.navigateUp()
    }
}