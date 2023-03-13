package com.rianwnoviantoro.jwt.interceptors

import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class XssProtectionInterceptor(): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val xssProtection = request.getHeader("X-XSS-Protection") ?: throw UnauthorizedException()

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