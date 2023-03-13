package com.rianwnoviantoro.jwt.interceptors

import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class TransportSecurityInterceptor(): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val transportSecurity = request.getHeader("Strict-Transport-Security") ?: throw UnauthorizedException()

        if (transportSecurity != "max-age=31536000; includeSubDomains; preload") {
            throw UnauthorizedException()
        }
        // valid
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        // nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        // nothing
    }
}