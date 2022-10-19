package com.example.lembretebeberagua

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.lembretebeberagua.databinding.ActivityMainBinding
import com.example.lembretebeberagua.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {



    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var bt_calcular: Button
    private lateinit var txt_result: TextView
    private lateinit var ic_refresh: ImageView

    private lateinit var bt_lembrete: Button
    private lateinit var bt_alarme: Button
    private lateinit var txt_hora: TextView
    private lateinit var txt_minutos: TextView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoML = 0.0

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var calendar: Calendar
    var horaAtual = 0
    var minutosAtuais = 0

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        bt_calcular.setOnClickListener {

            if (edit_peso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            } else if (edit_idade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            } else {
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
                resultadoML = calcularIngestaoDiaria.ResultadoML()
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false
                txt_result.text = formatar.format(resultadoML) + " " + "ml"
            }
        }

        ic_refresh.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("OK") { dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_hora.setText("00")
                    txt_minutos.setText("00")
                    txt_result.text = ""
                }
            alertDialog.setNegativeButton("Cancelar") { dialogInterface, i ->

            }
            val dialog = alertDialog.create()
            dialog.show()
        }

        bt_lembrete.setOnClickListener {

            calendar = Calendar.getInstance()
            horaAtual = calendar.get(Calendar.HOUR_OF_DAY)
            minutosAtuais = calendar.get(Calendar.MINUTE)
            timePickerDialog =
                TimePickerDialog(this, { timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                    txt_hora.text = String.format("%02d", hourOfDay)
                    txt_minutos.text = String.format("%02d", minutes)
                }, horaAtual, minutosAtuais, true)
            timePickerDialog.show()
        }

        bt_alarme.setOnClickListener {
            if (!txt_hora.text.toString().isEmpty() && !txt_minutos.text.toString().isEmpty()) {

                 Log.i("Entrou", "entrou na primeira parte")

                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hora.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minutos.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.alarme_mensagem))
                startActivity(intent)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)

                }
            }
        }

    }

    private fun IniciarComponentes() {

        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.bt_calcular)
        txt_result = findViewById(R.id.txt_result)
        ic_refresh = findViewById(R.id.ic_refresh)
        bt_lembrete = findViewById(R.id.bt_lembrete)
        bt_alarme = findViewById(R.id.bt_alarme)
        txt_hora = findViewById(R.id.txt_hora)
        txt_minutos = findViewById(R.id.txt_minutos)

    }

}