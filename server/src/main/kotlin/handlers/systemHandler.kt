package org.sean_tay.kotlin_react.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

fun systemHandler(): RoutingHttpHandler {
    val objectMapper = ObjectMapper()
    var inMemoryObject = mapOf<String, Any?>()

    return routes(
        "/config" bind Method.GET to { req ->
            Response(Status.OK)
                .header("Content-Type", "application/json;charset=utf-8")
                .body(objectMapper.writeValueAsString(inMemoryObject))
        },
        "/config" bind Method.POST to { req ->
            try {
                val newObject = objectMapper.readValue(req.bodyString(), Map::class.java)
                inMemoryObject = newObject as Map<String, Any?>

                Response(Status.OK)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(objectMapper.writeValueAsString(inMemoryObject))
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body(e.toString())
            }
        }
    )
}