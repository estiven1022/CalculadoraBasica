package com.example.calculadoraestiven

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var numero1: Double = 0.0
    var operacion: Int = 0
    lateinit var txt_num1: TextView
    lateinit var txt_num2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt_num1 = findViewById(R.id.txt_num1)
        txt_num2 = findViewById(R.id.txt_num2)
        val btnBorrar: Button = findViewById(R.id.buttonBorrar)
        val btnIgual: Button = findViewById(R.id.buttonIgual)

        btnIgual.setOnClickListener {
            val numero2: Double = txt_num2.text.toString().toDoubleOrNull() ?: 0.0
            val respuesta: Double = when (operacion) {
                1 -> numero1 + numero2
                2 -> numero1 - numero2
                3 -> numero1 * numero2
                4 -> if (numero2 != 0.0) numero1 / numero2 else Double.NaN
                else -> 0.0
            }

            txt_num2.text = respuesta.toString()
            txt_num1.text = ""
            operacion = 0
            numero1 = 0.0
        }

        btnBorrar.setOnClickListener {
            txt_num1.text = ""
            txt_num2.text = ""
            numero1 = 0.0
            operacion = 0
        }
    }

    fun precionarDigito(view: View) {
        val num2: String = txt_num2.text.toString()
        when (view.id) {
            R.id.button0 -> txt_num2.text = num2 + "0"
            R.id.button1 -> txt_num2.text = num2 + "1"
            R.id.button2 -> txt_num2.text = num2 + "2"
            R.id.button3 -> txt_num2.text = num2 + "3"
            R.id.button4 -> txt_num2.text = num2 + "4"
            R.id.button5 -> txt_num2.text = num2 + "5"
            R.id.button6 -> txt_num2.text = num2 + "6"
            R.id.button7 -> txt_num2.text = num2 + "7"
            R.id.button8 -> txt_num2.text = num2 + "8"
            R.id.button9 -> txt_num2.text = num2 + "9"
            R.id.buttonPunto -> txt_num2.text = num2 + "."
        }
    }

    fun clickOperacion(view: View) {
        numero1 = txt_num2.text.toString().toDoubleOrNull() ?: 0.0
        val num2Text = txt_num2.text.toString()
        txt_num2.text = ""

        when (view.id) {
            R.id.buttonSumar -> {
                txt_num1.text = "$num2Text +"
                operacion = 1
            }
            R.id.buttonRestar -> {
                txt_num1.text = "$num2Text -"
                operacion = 2
            }
            R.id.buttonMultiplicar -> {
                txt_num1.text = "$num2Text *"
                operacion = 3
            }
            R.id.buttonDividir -> {
                txt_num1.text = "$num2Text /"
                operacion = 4
            }
        }
    }
}
