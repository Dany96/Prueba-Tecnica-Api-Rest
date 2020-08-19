package com.webservice.webservice.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

class Strings {
    companion object {
        @JvmStatic lateinit var instance: Strings
        @JvmStatic lateinit var strings: JsonNode
        @JvmStatic lateinit var config: JsonNode
        @JvmStatic lateinit var routes: JsonNode

        @JvmStatic fun getString(key: String): String {
            var value = ""
            try {
                value = strings.findValue(key).asText()
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "getString", e)
            }

            return value
        }

        @JvmStatic fun setString() {
            try {
                val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("strings/mensajes.json")
                strings = jacksonObjectMapper().readTree(inputStream)
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "setString", e)
            }
        }

        @JvmStatic fun getConfig(key: String): String {
            var value = ""
            try {
                value = config.findValue(key).asText()
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "getConfig", e)
            }

            return value
        }

        @JvmStatic fun setConfig() {
            try {
                val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("strings/config.json")
                config = jacksonObjectMapper().readTree(inputStream)
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "setConfig", e)
            }
        }

        @JvmStatic fun getRoutes(key: String): String {
            var value = ""
            try {
                value = routes.findValue(key).asText()
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "getConfig", e)
            }

            return value
        }

        @JvmStatic fun setRoutes() {
            try {
                val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("strings/routes.json")
                routes = jacksonObjectMapper().readTree(inputStream)
            }
            catch (e: Exception) {
                msg(this.javaClass.name, "setConfig", e)
            }
        }
    }

    init {
        instance = this


    }




}