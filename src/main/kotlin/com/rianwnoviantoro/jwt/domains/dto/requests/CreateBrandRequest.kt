package com.rianwnoviantoro.jwt.domains.dto.requests

import javax.validation.constraints.NotEmpty

class CreateBrandRequest(
    @field:NotEmpty(message = "cd_brand is mandatory.")
    val cdBrand: String,

    @field:NotEmpty(message = "desc_brand is mandatory.")
    val descBrand: String,
)