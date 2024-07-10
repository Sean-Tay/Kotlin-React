package org.sean_tay.kotlin_react.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes

fun handlers(): HttpHandler {
    return routes(
        "/hello" bind Method.GET to {
            req -> Response(Status.OK).body("Hello World!")
        }
    )
}