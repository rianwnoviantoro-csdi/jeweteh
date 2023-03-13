package com.rianwnoviantoro.jwt.controllers

import com.rianwnoviantoro.jwt.domains.dto.responses.GlobalResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException
import com.rianwnoviantoro.jwt.error.NotFoundException
import com.rianwnoviantoro.jwt.error.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): ResponseEntity<Any> {

        val rawError = constraintViolationException.message!!.split(", ")
        val errors: ArrayList<String> = ArrayList()

        for (error in rawError) {
            error.let { errors.add(it.substringAfter(": ")) }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalResponse(
            out_stat = "F",
            out_mess = errors,
            out_data = null
        ))
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalResponse(
            out_stat = "F",
            out_mess = "Not found.",
            out_data = null
        ))
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun notFound(unauthorizedException: UnauthorizedException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponse(
            out_stat = "F",
            out_mess = "Unauthorized.",
            out_data = null
        ))
    }
}