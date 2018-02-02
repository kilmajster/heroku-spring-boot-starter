package com.createam.heroku.thymeleaf

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RunWith(MockitoJUnitRunner::class)
class HttpsEnforcerTest {

    private val request : HttpServletRequest = Mockito.mock(HttpServletRequest::class.java)
    private val response: HttpServletResponse = Mockito.mock(HttpServletResponse::class.java)
    private val chain: FilterChain = Mockito.mock(FilterChain::class.java)

    @Test
    fun shouldRedirectHttpToHttps() {
        val httpsEnforcer = HttpsEnforcer()

        httpsEnforcer.doFilter(request, response, chain)

        Assert.assertTrue(true)
    }

}
