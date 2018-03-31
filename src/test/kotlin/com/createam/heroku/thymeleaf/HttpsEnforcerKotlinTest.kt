package com.createam.heroku.thymeleaf

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RunWith(MockitoJUnitRunner::class)
class HttpsEnforcerKotlinTest {

    private val request: HttpServletRequest = mock(HttpServletRequest::class.java)
    private val response: HttpServletResponse = mock(HttpServletResponse::class.java)
    private val chain: FilterChain = mock(FilterChain::class.java)
    private val mockedFilterConfig = mock(FilterConfig::class.java)

    private var TEST_HEROKU_URL: String = "createam-labs.herokuapp.com"
    private var EXPECTED_HEROKU_URL: String = "https://createam-labs.herokuapp.com"

    @Test
    fun shouldEnforceHttpsOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader(Matchers.eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)

        httpsEnforcer.doFilter(request, response, chain)

        verify(chain, times(1)).doFilter(request, response)
    }

    @Test
    fun shouldRedirectToSameUrlOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader(Matchers.eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)
        `when`(request.serverName).thenReturn(TEST_HEROKU_URL)
        `when`(request.requestURI).thenReturn("/api")

        httpsEnforcer.doFilter(request, response, chain)

        val expectedUrl: String = EXPECTED_HEROKU_URL + "/api"
        verify(response, times(1)).sendRedirect(expectedUrl)
    }

    @Test
    fun shouldNotEnforceHttps_onHerokuOverHttps() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn("https")
        `when`(request.serverName).thenReturn(TEST_HEROKU_URL)
        `when`(request.requestURI).thenReturn("/api")

        httpsEnforcer.doFilter(request, response, chain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_whenHeaderIsEmpty() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn(null)

        httpsEnforcer.doFilter(request, response, chain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_overHttps() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn("https")

        httpsEnforcer.doFilter(request, response, chain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_onDestroy() {
        val httpsEnforcer = HttpsEnforcer()

        httpsEnforcer.destroy()

        verifyZeroInteractions(mockedFilterConfig)
    }

    @Test
    fun shouldInitWithFilterConfig() {
        val httpsEnforcer = HttpsEnforcer()

        httpsEnforcer.init(mockedFilterConfig)

        assertThat(httpsEnforcer.filterConfig).isEqualTo(mockedFilterConfig)
    }
}
