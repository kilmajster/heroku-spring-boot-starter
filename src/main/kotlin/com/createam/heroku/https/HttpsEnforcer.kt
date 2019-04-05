package com.createam.heroku.https

import javax.servlet.*
import javax.servlet.FilterConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class HttpsEnforcer: Filter {

    companion object {
        const val X_FORWARDED_PROTO: String = "x-forwarded-proto"
    }

    lateinit var filterConfig: FilterConfig

    override fun init(filterConfig: FilterConfig) {
        this.filterConfig = filterConfig
    }

    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain) {

        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse

        if (isHttpRequest(request)) {
            response.sendRedirect("https://${request.serverName}${request.requestURI}")
        }

        chain.doFilter(request, response)
    }

    fun isHttpRequest(request: HttpServletRequest) =
            request.getHeader(X_FORWARDED_PROTO) != null && request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0

    override fun destroy() {}
}
