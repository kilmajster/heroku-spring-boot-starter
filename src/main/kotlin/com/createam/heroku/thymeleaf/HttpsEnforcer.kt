package com.createam.heroku.thymeleaf

import javax.servlet.*
import javax.servlet.FilterConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class HttpsEnforcer(
        private var X_FORWARDED_PROTO: String = "x-forwarded-proto"
) : Filter {

    private lateinit var filterConfig: FilterConfig

    override fun init(filterConfig: FilterConfig) {
        this.filterConfig = filterConfig
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val request = request as HttpServletRequest
        val response = response as HttpServletResponse

        if (request.getHeader(X_FORWARDED_PROTO) != null
                && request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
            response.sendRedirect("https://" + request.serverName + request.requestURI)
        }

        chain!!.doFilter(request, response)
    }

    override fun destroy() {}
}