package br.com.alura.financask.model

import java.math.BigDecimal
import java.util.Calendar

class Transacao (val valor: BigDecimal,
                 val categoria: String = "Indefinida",
                 val tipo: Tipo,
                 val data: Calendar = Calendar.getInstance())
/*
class Transacao(valor: BigDecimal){
    var valor: BigDecimal = valor
        set(value) {
            field = value
        }
        get() {
            return field
        }
}
*/