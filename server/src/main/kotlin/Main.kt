package org.sean_tay.kotlin_react

import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.sean_tay.kotlin_react.handlers.handlers

fun main() {
    handlers().asServer(SunHttp(9000)).start()
}