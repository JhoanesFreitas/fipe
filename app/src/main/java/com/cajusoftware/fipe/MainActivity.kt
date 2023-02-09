package com.cajusoftware.fipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cajusoftware.fipe.data.database.domain.Vehicle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            (application as FipeApplication).appContainer.vehicleRepository.insertItem(
                Vehicle(
                    "004399-0",
                    "R$ 72.312,00",
                    "GM - Chevrolet",
                    "CRUZE HB Sport LT 1.8 16V FlexP. 5p Aut",
                    2016,
                    "Gasolina",
                    "fevereiro de 2023 ",
                    1,
                    "G"
                )
            )
        }
    }
}