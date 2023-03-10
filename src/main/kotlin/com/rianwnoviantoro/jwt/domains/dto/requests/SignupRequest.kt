package com.rianwnoviantoro.jwt.domains.dto.requests

import javax.validation.constraints.NotEmpty

class SignupRequest(
    @field:NotEmpty(message = "Name is mandatory.")
    val name: String,

    @field:NotEmpty(message = "Email is mandatory.")
    val email: String,

    @field:NotEmpty(message = "Password is mandatory.")
    val password: String
)