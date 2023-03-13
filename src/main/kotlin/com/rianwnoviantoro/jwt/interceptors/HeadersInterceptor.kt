package com.rianwnoviantoro.jwt.interceptors

import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class HeadersInterceptor(): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val contentTypeOption = request.getHeader("X-Content-Type-Options") ?: throw UnauthorizedException()
        val frame = request.getHeader("X-Frame-Options") ?: throw UnauthorizedException()
        val transportSecurity = request.getHeader("Strict-Transport-Security") ?: throw UnauthorizedException()
        val xssProtection = request.getHeader("X-XSS-Protection") ?: throw UnauthorizedException()

        if (contentTypeOption != "nosniff") {
            throw UnauthorizedException()
        }

        if (frame != "SAMEORIGIN") {
            throw UnauthorizedException()
        }

        if (transportSecurity != "max-age=31536000; includeSubDomains; preload") {
            throw UnauthorizedException()
        }

        if (xssProtection != "1; mode=block") {
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