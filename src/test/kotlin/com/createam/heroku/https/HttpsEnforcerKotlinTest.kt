package com.createam.heroku.https

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RunWith(MockitoJUnitRunner::class)
class HttpsEnforcerKotlinTest {

    companion object {
        const val TEST_HEROKU_URL: String = "createam-labs.herokuapp.com"
        const val EXPECTED_HEROKU_URL: String = "https://createam-labs.herokuapp.com"
    }

    lateinit var request: HttpServletRequest
    lateinit var response: HttpServletResponse
    lateinit var filterChain: FilterChain
    lateinit var filterConfig: FilterConfig

    @Before
    fun setUp() {
        request = mock(HttpServletRequest::class.java)
        response = mock(HttpServletResponse::class.java)
        filterChain = mock(FilterChain::class.java)
        filterConfig = mock(FilterConfig::class.java)
    }

    @Test
    fun shouldEnforceHttpsOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader(eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)

        httpsEnforcer.doFilter(request, response, filterChain)

        verify(filterChain).doFilter(request, response)
    }

    @Test
    fun shouldRedirectToSameUrlOnHeroku() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader(eq("x-forwarded-proto"))).thenReturn(TEST_HEROKU_URL)
        `when`(request.serverName).thenReturn(TEST_HEROKU_URL)
        `when`(request.requestURI).thenReturn("/api")

        httpsEnforcer.doFilter(request, response, filterChain)

        val expectedUrl = "$EXPECTED_HEROKU_URL/api"
        verify(response).sendRedirect(expectedUrl)
    }

    @Test
    fun shouldNotEnforceHttps_onHerokuOverHttps() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn("https")

        httpsEnforcer.doFilter(request, response, filterChain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_whenHeaderIsEmpty() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn(null)

        httpsEnforcer.doFilter(request, response, filterChain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_overHttps() {
        val httpsEnforcer = HttpsEnforcer()
        `when`(request.getHeader("x-forwarded-proto")).thenReturn("https")

        httpsEnforcer.doFilter(request, response, filterChain)

        verifyZeroInteractions(response)
    }

    @Test
    fun shouldDoNothing_onDestroy() {
        val httpsEnforcer = HttpsEnforcer()

        httpsEnforcer.destroy()

        verifyZeroInteractions(filterConfig)
    }

    @Test
    fun shouldInitWithFilterConfig() {
        val httpsEnforcer = HttpsEnforcer()

        httpsEnforcer.init(filterConfig)

        assertThat(httpsEnforcer.filterConfig).isEqualTo(filterConfig)
    }
}
