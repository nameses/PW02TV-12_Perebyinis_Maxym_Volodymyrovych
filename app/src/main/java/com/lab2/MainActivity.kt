package com.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val etCoalConsumption = findViewById<EditText>(R.id.etCoalConsumption)
        val etFuelOilConsumption = findViewById<EditText>(R.id.etFuelOilConsumption)
        val etGasConsumption = findViewById<EditText>(R.id.etGasConsumption)
        val tvCoalResult = findViewById<TextView>(R.id.tvCoalResult)
        val tvFuelOilResult = findViewById<TextView>(R.id.tvFuelOilResult)
        val tvGasResult = findViewById<TextView>(R.id.tvGasResult)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val coalConsumption = etCoalConsumption.text.toString().toDoubleOrNull() ?: 0.0
            val fuelOilConsumption = etFuelOilConsumption.text.toString().toDoubleOrNull() ?: 0.0
            val gasConsumption = etGasConsumption.text.toString().toDoubleOrNull() ?: 0.0

            val coalEmissions = calculateCoalEmissions(coalConsumption)
            val fuelOilEmissions = calculateFuelOilEmissions(fuelOilConsumption)
            val gasEmissions = calculateGasEmissions(gasConsumption)

            tvCoalResult.text = "Валовий викид (вугілля): ${String.format("%.2f", coalEmissions)} т"
            tvFuelOilResult.text = "Валовий викид (мазут): ${String.format("%.2f", fuelOilEmissions)} т"
            tvGasResult.text = "Валовий викид (природний газ): ${String.format("%.2f", gasEmissions)} т"
        }
    }

    private fun calculateCoalEmissions(coal: Double): Double {
        val emissionFactor = Math.pow(10.0, -6.0) * 150 * 20.47
        return coal * emissionFactor
    }

    private fun calculateFuelOilEmissions(fuelOil: Double): Double {
        val emissionFactor = Math.pow(10.0, -6.0) * 0.57 * 39.48
        return fuelOil * emissionFactor
    }

    private fun calculateGasEmissions(gas: Double): Double {
        return 0.0
    }
}
