package com.cajusoftware.fipe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.cajusoftware.fipe.databinding.FragmentHomeBinding
import com.cajusoftware.fipe.di.ViewModelProvider
import com.cajusoftware.fipe.ui.brands.adapters.BrandAdapter
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel
import com.cajusoftware.fipe.ui.brands.adapters.ModelAdapter
import com.cajusoftware.fipe.utils.NavUtils.navControllerDestinationChangeListener

class HomeFragment : Fragment() {

    private val vehicleBrandViewModel: VehicleBrandViewModel by viewModels { ViewModelProvider.Factory }

    private lateinit var navController: NavController

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.brandsRecyclerView.adapter = BrandAdapter(viewModel = vehicleBrandViewModel) {
            vehicleBrandViewModel.fetchBrandsModels(it)
            vehicleBrandViewModel.getBrandsModels(it)
        }
        binding.brandModelsRecyclerView.adapter =
            ModelAdapter(vehicleBrandViewModel, viewLifecycleOwner) { brandModel ->
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToVehicleFragment(
                        brandModel.code,
                        brandModel.name,
                        brandModel.brandNumber,
                        brandModel.brandName,
                    )
                )
            }
        binding.viewModel = vehicleBrandViewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(navControllerDestinationChangeListener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(navControllerDestinationChangeListener)
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }
}