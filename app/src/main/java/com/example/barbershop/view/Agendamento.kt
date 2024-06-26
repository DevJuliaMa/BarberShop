package com.example.barbershop.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

//    @RequiresApi(Build.VERSION_CODES.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
    datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dia = dayOfMonth.toString().padStart(2, '0')
        val mes = (monthOfYear + 1).toString().padStart(2, '0')

        data = "$dia/$mes/$year"
    }
        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String
            if(minute < 10) {
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }
            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {
            val barbeiro01 = binding.barbeiro1
            val barbeiro02 = binding.barbeiro2
            val barbeiro03 = binding.barbeiro3

            when{
                hora.isEmpty() -> {
                    mensagem(it, "Preencha o horário!",  "#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it, "Barbearia está fechada - horário de atendimento das 08:00 as 19:00!",  "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it, "Coloque uma data!",  "#FF0000")
                }
                barbeiro01.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!",  "#FF03DAC5")
                }
                barbeiro02.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!",  "#FF03DAC5")
                }
                barbeiro03.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso!",  "#FF03DAC5")
                }
                else -> {
                    mensagem(it, "Escolha um barbeiro!",  "#FF0000")
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}