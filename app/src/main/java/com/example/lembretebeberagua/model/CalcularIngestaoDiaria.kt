package com.example.lembretebeberagua.model

class CalcularIngestaoDiaria {
    private val ML_JOVEM = 40.0
    private val ML_ADULTO = 35.0
    private val ML_IDOSO = 30.0
    private val ML_MAIS_DE_66_ANOS = 25.0

    private var resultML = 0.0
    private var resultTotal = 0.0

    fun CalcularTotalMl(peso: Double, idade: Int) {

        if (idade <= 17) {
            resultML = peso * ML_JOVEM
            resultTotal = resultML

        } else if (idade <= 55) {
            resultML = peso * ML_ADULTO
            resultTotal = resultML
        } else if (idade <= 65) {
            resultML = peso * ML_IDOSO
            resultTotal = resultML
        }else {
            resultML = peso * ML_MAIS_DE_66_ANOS
            resultTotal = resultML

        }
    }

    fun ResultadoML(): Double{
        return resultTotal
    }
}