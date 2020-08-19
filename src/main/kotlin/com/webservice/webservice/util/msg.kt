package com.webservice.webservice.util

import com.webservice.webservice.Model.Mensaje


/**
 * Imprime mensajes en consola
 * @param {Any} contexto - Contexto de la clase que solicita la impresion
 * @param {String} titulo - Mensaje de titulo
 * @param {Any} valor - Objeto que se desea imprimir
 */
fun msg(contexto: Any?, titulo: String?, valor: Any?) {
    try {
        println("TITULO -> " + titulo + " <- ")
        println(contexto)
        println(valor)
    }
    catch (e: Exception) {
        println("Error")
        println(e.message)
        println(e.cause.toString())
    }
}

fun respuesta(codigo: Int): Mensaje {
    try {
        return Mensaje(codigo, Strings.getString(codigo.toString()))
    }
    catch (e: Exception){
        msg("msg.kt", "respuesta", e)
        return Mensaje(100001, Strings.getString("100001"))
    }

}

fun msgError(context: Any?, e: Exception?) {
    try {
        if (context != null)
            println(context)

        if (e != null) {
            println(e.message)
            println(e.cause)
            println(e.printStackTrace())
        }
    }
    catch (e: Exception) {
        println("Error")
        println(e.message)
        println(e.cause.toString())
    }
}
