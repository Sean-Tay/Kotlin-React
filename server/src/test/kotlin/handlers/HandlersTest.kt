package handlers

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.sean_tay.kotlin_react.handlers.handlers

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandlersTest {
    @Test
    fun `should be able to call endpoint`() {
        assertThat(handlers()(Request(Method.GET, "/hello")), equalTo(Response(Status.OK).body("Hello World!")))
    }
}