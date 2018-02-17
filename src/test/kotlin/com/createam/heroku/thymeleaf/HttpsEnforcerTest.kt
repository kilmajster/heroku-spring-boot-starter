package com.createam.heroku.thymeleaf

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RunWith(MockitoJUnitRunner::class)
class HttpsEnforcerTest{

    private val request : HttpServletRequest = Mockito.mock(HttpServletRequest::class.java)
    private val response: HttpServletResponse = Mockito.mock(HttpServletResponse::class.java)
    private val chain: FilterChain = Mockito.mock(FilterChain::class.java)

    private var TEST_HEROKU_URL: String = "http://for-sure-no-existing-address.xyz"

    @Test
    fun shouldEnforceHttpsOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        mockEnviroment()
        httpsEnforcer.doFilter(request, response, chain)

        Mockito.verify(chain, Mockito.times(1)).doFilter(request, response)
    }

    @Test
    fun shouldNotEnforceHttpsOnLocal() {

    }

    @Test
    fun shouldRedirectToSameUrlOnHeroku() {

    }

    @Test
    fun shouldRedirectToSameUrlOnLocal() {

    }

    fun mockEnviroment() {
        Mockito.`when`(request.getHeader(Matchers.eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)
    }


}
