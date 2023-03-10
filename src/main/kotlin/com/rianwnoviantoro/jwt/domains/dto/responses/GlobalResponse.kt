package com.rianwnoviantoro.jwt.domains.dto.responses

class GlobalResponse<T>(
    val out_stat: String,
    val out_mess: String,
    val out_data: T?
)