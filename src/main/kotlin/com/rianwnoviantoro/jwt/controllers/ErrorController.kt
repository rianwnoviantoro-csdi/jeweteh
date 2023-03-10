package com.rianwnoviantoro.jwt.controllers

import com.rianwnoviantoro.jwt.domains.dto.responses.GlobalResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException
import com.rianwnoviantoro.jwt.error.NotFoundException
import com.rianwnoviantoro.jwt.error.UnauthorizedException

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): GlobalResponse<Any> {

        val rawError = constraintViolationException.message!!.split(", ")
        val errorlist: ArrayList<String> = ArrayList()

        for (error in rawError) {
            error.let { errorlist.add(it.substringAfter(": ")) }
        }

        return GlobalResponse(
            out_stat = "F",
            out_mess = "Bad request.",
            out_data = errorlist
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): GlobalResponse<String> {
        return GlobalResponse(
            out_stat = "F",
            out_mess = "Not found.",
            out_data = null
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun notFound(unauthorizedException: UnauthorizedException): GlobalResponse<String> {
        return GlobalResponse(
            out_stat = "F",
            out_mess = "Unauhorized.",
            out_data = null
        )
    }
}