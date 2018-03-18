package com.createam.heroku.thymeleaf

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RunWith(MockitoJUnitRunner::class)
class HttpsEnforcerTest {

    private val request: HttpServletRequest = Mockito.mock(HttpServletRequest::class.java)
    private val response: HttpServletResponse = Mockito.mock(HttpServletResponse::class.java)
    private val chain: FilterChain = Mockito.mock(FilterChain::class.java)

    private var TEST_HEROKU_URL: String = "for-sure-no-existing-addressaddress.xyz"
    private var EXPECTED_HEROKU_URL: String = "https://for-sure-no-existing-addressaddress.xyz"

    @Test
    fun shouldEnforceHttpsOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        Mockito.`when`(request.getHeader(Matchers.eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)
        httpsEnforcer.doFilter(request, response, chain)

        Mockito.verify(chain, Mockito.times(1)).doFilter(request, response)
    }

    @Test
    fun shouldRedirectToSameUrlOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        Mockito.`when`(request.getHeader(Matchers.eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)
        Mockito.`when`(request.serverName).thenReturn(TEST_HEROKU_URL)
        Mockito.`when`(request.requestURI).thenReturn("/api")

        httpsEnforcer.doFilter(request, response, chain)

        val expectedUrl: String = EXPECTED_HEROKU_URL + "/api"
        Mockito.verify(response, Mockito.times(1)).sendRedirect(expectedUrl)
    }
}
