package com.rianwnoviantoro.jwt.interceptors

import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ContentTypeOptionsInterceptor(): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val contentTypeOption = request.getHeader("X-Content-Type-Options") ?: throw UnauthorizedException()

        if (contentTypeOption != "nosniff") {
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