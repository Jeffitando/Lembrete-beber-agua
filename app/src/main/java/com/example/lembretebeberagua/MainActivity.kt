package com.example.lembretebeberagua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lembretebeberagua.model.CalcularIngestaoDiaria

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso:EditText
    private lateinit var edit_idade:EditText
    private lateinit var bt_calcular:Button
    private lateinit var txt_result:TextView
    private lateinit var ic_refresh:ImageView

    private lateinit var calcularIngestaoDiaria:CalcularIngestaoDiaria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        bt_calcular.setOnClickListener {

            if (edit_peso.text.toString().isEmpty()){
                Toast.makeText(this,R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            }else if (edit_idade.text.toString().isEmpty()){
                Toast.makeText(this,R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            }else{
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
            }
        }

    }

    private fun IniciarComponentes(){
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.bt_calcular)
        txt_result = findViewById(R.id.txt_result)
        ic_refresh = findViewById(R.id.ic_refresh)
    }

}