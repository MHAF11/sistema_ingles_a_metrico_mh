package net.javiersg.sistema_ingles_a_metrico

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Vinculación de vistas
        val spinnerFrom: Spinner = findViewById(R.id.spinner1)
        val spinnerTo: Spinner = findViewById(R.id.spinner2)
        val inputQuantity: EditText = findViewById(R.id.editTextNumberDecimal)
        val calculateButton: Button = findViewById(R.id.button1)
        val resultTextView: TextView = findViewById(R.id.textView3)

        // Configurar Spinner con opciones de unidades
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.Spinner1))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        // Configuración del botón de cálculo
        calculateButton.setOnClickListener {
            val fromUnit = spinnerFrom.selectedItem.toString()
            val toUnit = spinnerTo.selectedItem.toString()
            val quantityStr = inputQuantity.text.toString()

            // Validar si el usuario ingresó una cantidad
            if (quantityStr.isEmpty()) {
                resultTextView.text = "Por favor, ingrese una cantidad válida"
                return@setOnClickListener
            }

            try {
                val quantity = quantityStr.toDouble()
                val result = convertUnits(fromUnit, toUnit, quantity)

                // Mostrar el resultado en el TextView3
                resultTextView.text = "Resultado: $result $toUnit"
            } catch (e: Exception) {
                resultTextView.text = "Error en la conversión"
                e.printStackTrace()
            }
        }
    }

    // Función para realizar la conversión de unidades
    private fun convertUnits(fromUnit: String, toUnit: String, quantity: Double): Double {
        return when (fromUnit to toUnit) {
            "Pulgadas" to "Centimetros" -> quantity * 2.54
            "Pulgadas" to "Milimetros" -> quantity * 25.4
            "Pulgadas" to "metros" -> quantity * 2.54
            "Pulgadas" to "yardas" -> quantity * 25.4
            "Pulgadas" to "pies" -> quantity * 25.4

            "pies" to "Centimetros" -> quantity * 30.48
            "pies" to "Milimetros" -> quantity * 304.8
            "pies" to "metros" -> quantity * 0.3048
            "pies" to "yardas" -> quantity * 20.333333
            "pies" to "pulgadas" -> quantity * 12


            "Centimetros" to "Pulgadas" -> quantity / 2.54
            "Centimetros" to "Milimetros" -> quantity * 10
            "Centimetros" to "metros" -> quantity * 0.01
            "Centimetros" to "pies" -> quantity * 0.0328084
            "Centimetros" to "yardas" -> quantity * 0.0109361

            "Milimetros" to "Pulgadas" -> quantity / 25.4
            "Milimetros" to "Centimetros" -> quantity / 10
            "Milimetros" to "yardas" -> quantity * 0.00109361
            "Milimetros" to "pies" -> quantity * 0.00328084
            "Milimetros" to "metros" -> quantity * 0.001

            "yardas" to "pies" -> quantity  * 3
            "yardas" to "metros" -> quantity  * 0.9144
            "yardas" to "centimetros" -> quantity  * 91.44
            "yardas" to "milimetros" -> quantity  * 914.4
            "yardas" to "pulgadas" -> quantity  * 36

            "metros" to "pies" -> quantity * 3.28084
            "metros" to "yardas" -> quantity * 1.09361
            "metros" to "Centimetros" -> quantity * 100
            "metros" to "milimetros" -> quantity * 1000
            "metros" to "pulgadas" -> quantity * 39.3701




            "Inch" to "Centimeters" -> quantity * 2.54
            "inches" to  "Milimeters" -> quantity * 25.4
            "Centimeters" to "Inch" -> quantity / 2.54
            "Centimeters" to "Milimeters" -> quantity * 10
            "Milimeters" to "Inch" -> quantity / 25.4
            "Milimeters" to "Centimeters" -> quantity / 10

            "feet" to "Centimeters" -> quantity * 30.48
            "feet" to "Milimeters" -> quantity * 304.8
            "feet" to "Meters" -> quantity * 0.3048
            "feet" to "yards" -> quantity * 20.333333
            "feet" to "Inch" -> quantity * 12

            "Inch" to "Centimeters" -> quantity * 2.54
            "Inch" to "Milimeters" -> quantity * 25.4
            "Inch" to "Meters" -> quantity * 0.0254
            "Inch" to "yards" -> quantity * 0.0277778
            "Inch" to "feet" -> quantity * 0.0833333

            "Centimeters" to "Inch" -> quantity / 2.54
            "Centimeters" to "Milimeters" -> quantity * 10
            "Centimeters" to "Meters" -> quantity * 0.01
            "Centimeters" to "feet" -> quantity * 0.0328084
            "Centimeters" to "yards" -> quantity * 0.0109361

            "Milimeters" to "Inch" -> quantity / 25.4
            "Milimeters" to "Centimeters" -> quantity / 10
            "Milimeters" to "yards" -> quantity * 0.00109361
            "Milimeters" to "feet" -> quantity * 0.00328084
            "Milimeters" to "Meters" -> quantity * 0.001

            "yards" to "feet" -> quantity * 3
            "yards" to "Meters" -> quantity * 0.9144
            "yards" to "Centimeters" -> quantity * 91.44
            "yards" to "Milimeters" -> quantity * 914.4
            "yards" to "Inch" -> quantity * 36

            "Meters" to "feet" -> quantity * 3.28084
            "Meters" to "yards" -> quantity * 1.09361
            "Meters" to "Centimeters" -> quantity * 100
            "Meters" to "Milimeters" -> quantity * 1000
            "Meters" to "Inch" -> quantity * 39.3701
            else -> quantity // Si las unidades son iguales
        }
    }
}
