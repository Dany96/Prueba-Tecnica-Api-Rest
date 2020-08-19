package com.webservice.webservice

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import com.webservice.webservice.Model.Data
import com.webservice.webservice.Model.Moneda
import com.webservice.webservice.Repository.GetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import com.webservice.webservice.util.*
import org.json.JSONObject
import java.util.concurrent.*

@SpringBootApplication
class WebserviceApplication

fun main(args: Array<String>) {
	runApplication<WebserviceApplication>(*args)
}

//LOGICA PARA EJECUTAR EL EJERCICIO N#2
@Component
internal class Get : CommandLineRunner {
	@Autowired
	private val repository: GetRepository? = null

	@Throws(Exception::class)
	override fun run(vararg args: String) {
		timer()
	}

	//OBTIENE LOS DATOS DE LA URL
 	fun get(url: String): Any? {
     var jsonResponse: HttpResponse<JsonNode>? = null
     try {
         jsonResponse = Unirest.get(url)
                 .asJson()
     } catch (e: Exception) {
         msgError(this.javaClass.name, e)
         return anyToJson(respuesta(100006))
     }
     //println(jsonResponse!!.body)
     return jsonResponse!!.body
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

	//RECORRE EL JSON Y ALMACENA LOS DATOS
	 fun recorre(): Any? {
     val jsonArray = JSONObject(get("https://dweet.io:443/get/latest/dweet/for/thecore"))
     val array = jsonArray.getJSONObject("object").getJSONArray("with").getJSONObject(0).getJSONObject("content")
     val data = Data()
     data!!.temperature = array.get("temperature").toString()
     data!!.humidity = array.get("humidity").toString()
     repository!!.save(data)
     return null
 }

	//PRESENTA EL JSON
	fun presenta(){
		val list = repository!!.findAll() as List<Data>
		post("https://webhook.site/6f7c6822-4237-4e18-899b-87aaedf728a3",list.toString())
	}

	//LOGICA PARA INSERTAR DATA CADA MINUTO Y PRESENTARLA DESPUES DE 15 MINUTOS
 	fun timer() {
     val scheduler = Executors.newScheduledThreadPool(1)
     val beeper = Runnable { recorre() }
     val beeperHandle = scheduler.scheduleAtFixedRate(beeper, 60, 60, TimeUnit.SECONDS)
     scheduler.schedule(Runnable { beeperHandle.cancel(true)
		 presenta()
	 }, 60 * 15, TimeUnit.SECONDS)
 }
}