package com.example.creditomya

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}

            val valorPrestamoEditText: EditText = findViewById(R.id.cuantoNecesitasNum)
            val tasaInteresEditText: EditText = findViewById(R.id.tasaInteresNum)
            val plazoAnosEditText: EditText = findViewById(R.id.plazoCreditoNum)
            val calcularButton: Button = findViewById(R.id.simularbtn)
            val resultadoTextView: TextView = findViewById(R.id.resultadotxt)

            calcularButton.setOnClickListener {
                val valorPrestamo = valorPrestamoEditText.text.toString().toDoubleOrNull()
                val tasaInteres = tasaInteresEditText.text.toString().toDoubleOrNull()
                val plazoAnos = plazoAnosEditText.text.toString().toIntOrNull()

                if (valorPrestamo != null && tasaInteres != null && plazoAnos != null) {
                    val cuotaMensual = calcularCuotaMensual(valorPrestamo, tasaInteres, plazoAnos)
                    resultadoTextView.text = "Cuota mensual: $cuotaMensual"
                } else {
                    resultadoTextView.text = "Ingresa todos los datos correctamente"
                }
            }
        }

    private fun calcularCuotaMensual(valorPrestamo: Double, tasaInteres: Double, plazoAnos: Int): Double {
        val tasaInteresMensual = tasaInteres / 12 / 100
        val plazoMeses = plazoAnos * 12
        return valorPrestamo * (tasaInteresMensual * Math.pow(1 + tasaInteresMensual, plazoMeses.toDouble())) /
                (Math.pow(1 + tasaInteresMensual, plazoMeses.toDouble()) - 1)
    }
}
