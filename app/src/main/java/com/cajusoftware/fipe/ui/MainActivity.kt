package com.cajusoftware.fipe.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cajusoftware.fipe.databinding.ActivityMainBinding
import com.cajusoftware.fipe.di.ViewModelProvider.Factory
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel

class MainActivity : AppCompatActivity() {

    private val vehicleBrandViewModel: VehicleBrandViewModel by viewModels { Factory }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = vehicleBrandViewModel

        vehicleBrandViewModel.getAll()
    }
}