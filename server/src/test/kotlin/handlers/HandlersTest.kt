package handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.http4k.core.Request
import org.sean_tay.kotlin_react.handlers.handlers

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandlersTest {
    val objectMapper = ObjectMapper()

    @Test
    fun `should be able to save and read config via endpoint`() {
        val handler = handlers()
        handler(Request(Method.POST, "/api/system/config").body(objectMapper.writeValueAsString(mapOf("Hello" to "World"))))

        val response = handler(Request(Method.GET, "/api/system/config"))
        assertThat(objectMapper.readValue(response.bodyString(), Map::class.java), equalTo(mapOf("Hello" to "World")))
    }
}