package com.webservice.webservice.util

import java.text.SimpleDateFormat
import java.util.*
import jdk.nashorn.internal.objects.NativeDate.getTime
import java.util.Calendar


/**
 * Formatos de fecha
 * dd/MM/yyyy
 * MM/dd/yyyy
 * yyyy/MM/dd
 */
val formatDeafult = SimpleDateFormat("dd/MM/yyyy")

/**
 * Retorna un objeto fecha de un string con formato
 */
fun getDateByFormat(_format: String, date: String?): Date {
    var format = formatDeafult
    var dateResult = Date()

    try {
        if (_format.isNotEmpty())
            format = SimpleDateFormat(_format)

        if (date!!.isNotEmpty()) {
            dateResult = format.parse(date)
        }

    }
    catch (e: Exception) {
        msg("datetime.kt", "getDateByFormat", e)
    }

    return dateResult
}

fun getDateStringByFormat(_format: String, _date: Date?): String {

    var format = formatDeafult
    var dateResult = ""
    var date = Date()

    try {
        if (_format.isNotEmpty())
            format = SimpleDateFormat(_format)

        if (_date != null)
            date = _date

        val c = Calendar.getInstance()
        c.time = date

        dateResult = getDateStringByFormat(_format, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))

    }
    catch (e: Exception) {
        msg("datetime.kt", "getDateStringByFormat", e)
    }

    return dateResult

}

fun getDateStringByFormat(_format: String, year: Int, month: Int, day: Int): String {
    var newDate = ""
    val _month = if(month < 10) "0$month" else "$month"
    val _day = if(day < 10) "0$day" else "$day"

    try {
        if (_format == "MM/dd/yyyy")
            newDate = "$_month/$_day/$year"  //month.toString() + "/$day/" + year
        else if (_format == "yyyy/MM/dd")
            newDate = "$year/$_month/$_day"  //year + "/" + newMonth.toString() + "/01"
        else
            newDate = "$_day/$_month/$year" //"01/" + newMonth.toString() + "/" + year
    }
    catch (e: Exception) {
        msg("datetime.kt", "getDateByFormat", e)
    }

    return newDate
}

fun addMonth(_format: String, date: Date, operation: String, months: Int):Date {
    val defaultDate= date //getDateByFormat(_format, date)
    var newDate = Date()

    try {

        val c = Calendar.getInstance()
        var newMonth = 0
        var _newDate = ""
        var year = c.get(Calendar.YEAR)

        c.time = defaultDate

        // Se debe de agrear +1 al obtener el mes de Calendar por los meses los maneja del 0 al 11
        if (operation == "+")
            newMonth = c.get(Calendar.MONTH) + 1 + months
        else
            newMonth = c.get(Calendar.MONTH) + 1 - months

        _newDate = getDateStringByFormat(_format, year, newMonth, 1)
        newDate = getDateByFormat(_format, _newDate)

        msg("datetime.kt", "addMonth", newDate)

    }
    catch (e: Exception) {
        msg("datetime.kt", "addMonth", e)
    }

    return newDate

}