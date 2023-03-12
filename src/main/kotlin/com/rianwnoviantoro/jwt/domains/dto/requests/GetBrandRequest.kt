package com.rianwnoviantoro.jwt.domains.dto.requests

import javax.validation.constraints.Pattern

class GetBrandRequest(
    @field:Pattern(regexp = "^[A-Za-z-0-9]*\$", message = "Invalid input")
    val search: String?
)