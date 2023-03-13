package com.rianwnoviantoro.jwt.configs

import com.rianwnoviantoro.jwt.interceptors.HeadersInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
@Component
class InterceptorRegistry(
    val headersInterceptor: HeadersInterceptor): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addWebRequestInterceptor(headersInterceptor)
    }
}