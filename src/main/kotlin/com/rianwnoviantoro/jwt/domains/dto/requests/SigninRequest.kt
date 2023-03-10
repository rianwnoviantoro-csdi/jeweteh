package com.rianwnoviantoro.jwt.domains.dto.requests

import javax.validation.constraints.NotEmpty

class SigninRequest(
    @field:NotEmpty(message = "Email is mandatory.")
    val email: String,

    @field:NotEmpty(message = "Password is mandatory.")
    val password: String
)