package com.rianwnoviantoro.jwt.configs

import com.rianwnoviantoro.jwt.interceptors.ContentTypeOptionsInterceptor
import com.rianwnoviantoro.jwt.interceptors.FrameOptionsInterceptor
import com.rianwnoviantoro.jwt.interceptors.TransportSecurityInterceptor
import com.rianwnoviantoro.jwt.interceptors.XssProtectionInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
@Component
class InterceptorRegistry(
    val contentTypeOptionsInterceptor: ContentTypeOptionsInterceptor,
    val xssProtectionInterceptor: XssProtectionInterceptor,
    val transportSecurityInterceptor: TransportSecurityInterceptor,
    val frameOptionsInterceptor: FrameOptionsInterceptor): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addWebRequestInterceptor(contentTypeOptionsInterceptor)
        registry.addWebRequestInterceptor(xssProtectionInterceptor)
        registry.addWebRequestInterceptor(transportSecurityInterceptor)
        registry.addWebRequestInterceptor(frameOptionsInterceptor)
    }
}