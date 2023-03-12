package com.rianwnoviantoro.jwt.services

import com.rianwnoviantoro.jwt.domains.dto.requests.CreateBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.GetBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.SignupRequest
import com.rianwnoviantoro.jwt.domains.entities.BrandEntity
import java.util.Optional

interface BrandService {
    fun save(body: CreateBrandRequest)

    fun findWithFilter(body: GetBrandRequest): List<BrandEntity>
}