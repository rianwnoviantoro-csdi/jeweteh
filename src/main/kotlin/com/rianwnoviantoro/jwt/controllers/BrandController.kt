package com.rianwnoviantoro.jwt.controllers

import com.rianwnoviantoro.jwt.domains.dto.requests.CreateBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.GetBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.SigninRequest
import com.rianwnoviantoro.jwt.domains.dto.responses.GlobalResponse
import com.rianwnoviantoro.jwt.domains.dto.responses.JwtResponse
import com.rianwnoviantoro.jwt.services.BrandService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.ArrayList
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/brands")
class BrandController(private val brandService: BrandService) {
    @PostMapping("/create")
    fun create(@RequestBody body: CreateBrandRequest, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            val list: ArrayList<String> = ArrayList()

            for (err in errors.allErrors) {
                err.defaultMessage?.let { list.add(it) }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                GlobalResponse(
                out_stat = "F",
                out_mess = "Failed.",
                out_data = list
            )
            )
        }

        brandService.save(body)

        return ResponseEntity.status(HttpStatus.OK).body(
            GlobalResponse(
            out_stat = "T",
            out_mess = "Added.",
            out_data = null
        )
        )
    }

    @PostMapping("/getAll")
    fun getAllBrands(@RequestBody body: GetBrandRequest, response: HttpServletResponse, errors: Errors): ResponseEntity<Any> {
        val brand = brandService.findWithFilter(body)

        return ResponseEntity.ok(GlobalResponse(
            out_stat = "T",
            out_mess = "Success.",
            out_data = brand
        ))
    }
}