package org.sean_tay.kotlin_react.handlers

import org.http4k.routing.*

fun handlers(): RoutingHttpHandler {
    return routes(
        "/api" bind apiHandler(),

        singlePageApp()
    )
}