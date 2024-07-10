package org.sean_tay.kotlin_react.handlers

import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

fun handlers(): RoutingHttpHandler {
    return routes(
        "/api" bind apiHandler(),
    )
}