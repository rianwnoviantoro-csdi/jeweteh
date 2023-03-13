package com.rianwnoviantoro.jwt.interceptors

import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class FrameOptionsInterceptor(): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val frame = request.getHeader("X-Frame-Options") ?: throw UnauthorizedException()

        if (frame != "SAMEORIGIN") {
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