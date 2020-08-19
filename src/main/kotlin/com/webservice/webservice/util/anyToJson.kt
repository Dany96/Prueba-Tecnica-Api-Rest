package com.webservice.webservice.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun anyToJson(obj: Any?): String {

    var result = ""

    try {
        result = jacksonObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj)
    }
    catch (e: Exception) {
        msg("anyToJson", "anyToJson", e)
    }

    return result
}