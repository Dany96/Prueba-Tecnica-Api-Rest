package com.webservice.webservice.Controller

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import com.webservice.webservice.Model.Moneda
import com.webservice.webservice.Repository.CambioRepository
import com.webservice.webservice.Repository.MonedaRepository
import com.webservice.webservice.util.anyToJson
import com.webservice.webservice.util.msgError
import com.webservice.webservice.util.respuesta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import java.util.*
//import javax.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping


@RestController
@RequestMapping("/moneda")
internal class MonedaController {

    @Autowired
    var monedaRepository: MonedaRepository? = null
    @Autowired
    var cambioRepository: CambioRepository? = null

    //GET BY ID
    @GetMapping("/getAll")
    fun getAllMoneda(@RequestParam(name="sigla") sigla: String,
                     @RequestParam(name="valor") valor: String): List<Moneda> {
        var moneda=""
        if(sigla=="EUR")
            moneda="Euro"

        if(sigla=="CLP")
            moneda="Pesos Chilenos"

        if(sigla=="PEN")
            moneda="Soles"

        val dato=cambioRepository!!.findByAllMoneda(sigla)
        for(i in 0..dato!!.size -1){
            val resultado=(valor.toDouble() * dato.get(i).cambio!!.toDouble())
            val newMoneda=Moneda()
            newMoneda.nombre=moneda
            newMoneda.sigla=sigla
            newMoneda.valor=valor
            newMoneda.cambio=dato.get(i).cambio
            newMoneda.dia=dato.get(i).fecha
            newMoneda.resultado=resultado.toString()
            monedaRepository!!.save(newMoneda)
        }
        post("https://webhook.site/6f7c6822-4237-4e18-899b-87aaedf728a3",monedaRepository!!.findByMoneda(sigla).toString())
        return monedaRepository!!.findByMoneda(sigla)!!
    }

    //GET BY ID
    @GetMapping("/getResult")
    fun getMonedaById(@RequestParam(name="sigla") sigla: String,
                      @RequestParam(name="valor") valor: String,
                      @RequestParam(name="dia") dia: String): ResponseEntity<Moneda> {
        var moneda=""
        if(sigla=="EUR")
            moneda="Euro"

        if(sigla=="CLP")
            moneda="Pesos Chilenos"

        if(sigla=="PEN")
            moneda="Soles"

        val dato=cambioRepository!!.findByMoneda(sigla,dia)
        val resultado=(valor.toDouble() * dato!!.cambio!!.toDouble())
        val newMoneda=Moneda()
        newMoneda.nombre=moneda
        newMoneda.sigla=sigla
        newMoneda.valor=valor
        newMoneda.cambio=dato.cambio
        newMoneda.dia=dia
        newMoneda.resultado=resultado.toString()
        monedaRepository!!.save(newMoneda)
        post("https://webhook.site/6f7c6822-4237-4e18-899b-87aaedf728a3",monedaRepository!!.findByValor(valor,dia).toString())

        return monedaRepository!!.findByValor(valor,dia)!!.map { moneda ->
            ResponseEntity.ok(moneda)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun post(url: String, body:String): Any? {
        var jsonResponse: HttpResponse<JsonNode>? = null
        try {
            jsonResponse = Unirest.post(url)
                    .body(body)
                    .asJson()
        }
        catch (e: Exception) {
            msgError(this.javaClass.name, e)
            return anyToJson(respuesta(100006))
        }

        return jsonResponse!!.body
    }

}