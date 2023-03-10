package com.rianwnoviantoro.jwt.controllers

import com.rianwnoviantoro.jwt.domains.dto.requests.SigninRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.SignupRequest
import com.rianwnoviantoro.jwt.domains.dto.responses.GlobalResponse
import com.rianwnoviantoro.jwt.domains.dto.responses.JwtResponse
import com.rianwnoviantoro.jwt.error.UnauthorizedException
import com.rianwnoviantoro.jwt.services.UserService
import com.rianwnoviantoro.jwt.utils.JwtUtil
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/")
class AuthController(private val userService: UserService, private val jwtUtil: JwtUtil) {
    @PostMapping("/signup")
    fun signup(@RequestBody body: SignupRequest, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            val list: ArrayList<String> = ArrayList()

            for (err in errors.allErrors) {
                err.defaultMessage?.let { list.add(it) }
            }

            return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse(
                out_stat = "F",
                out_mess = "Failed.",
                out_data = list
            ))
        }

        userService.save(body)

        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse(
            out_stat = "T",
            out_mess = "Signed up.",
            out_data = null
        ))
    }

    @PostMapping("/signin")
    fun signin(@RequestBody body: SigninRequest, response: HttpServletResponse, errors: Errors): ResponseEntity<Any> {
        val user = userService.findByEmail(body)

        if (!BCryptPasswordEncoder().matches(body.password, user.get().password)) {
            throw UnauthorizedException()
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalResponse(
//                out_stat = "F",
//                out_mess = "Invalid credentials.",
//                out_data = null
//            ))
        }

        val jwt = jwtUtil.generateJwtToken("http://localhost:8000", user.get())

        val cookie= Cookie("token", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(GlobalResponse(
            out_stat = "T",
            out_mess = "Signed in.",
            out_data = JwtResponse(token = jwt)
        ))
    }

    @GetMapping("/me")
    fun profile(@CookieValue("token") token: String?): ResponseEntity<Any> {
        try {
            if (token === null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    GlobalResponse(
                        out_stat = "F",
                        out_mess = "Unauthorized.",
                        out_data = null
                    )
                )
            }

            val body = Jwts.parser().setSigningKey("s3CreTK3y").parseClaimsJws(token).body

            return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse(
                out_stat = "T",
                out_mess = "Success",
                out_data = userService.getById(UUID.fromString(body.subject))
            ))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                GlobalResponse(
                    out_stat = "F",
                    out_mess = "Unauthorized.",
                    out_data = null
                )
            )
        }
    }

    @PostMapping("/signout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("token", "")
        cookie.maxAge = 0
        response.addCookie(cookie)

        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse(
            out_stat = "T",
            out_mess = "Signed out.",
            out_data = null
        ))
    }
}