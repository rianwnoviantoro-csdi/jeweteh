package com.rianwnoviantoro.jwt.services.implement

import com.rianwnoviantoro.jwt.domains.dto.requests.CreateBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.GetBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.responses.BrandResponse
import com.rianwnoviantoro.jwt.domains.entities.BrandEntity
import com.rianwnoviantoro.jwt.error.NotFoundException
import com.rianwnoviantoro.jwt.repositories.BrandRepository
import com.rianwnoviantoro.jwt.services.BrandService
import com.rianwnoviantoro.jwt.utils.ValidationUtils
import org.springframework.stereotype.Service
import java.util.*

@Service
class BrandServiceImplement(
    private val validationUtils: ValidationUtils,
    private val brandRepository: BrandRepository
): BrandService {
    override fun save(body: CreateBrandRequest) {
        validationUtils.validate(body)

        val brand = BrandEntity()

        brand.cd_brand = body.cdBrand
        brand.desc_brand = body.descBrand

        brandRepository.save(brand)
    }

    override fun findWithFilter(body: GetBrandRequest): List<BrandResponse> {
        validationUtils.validate(body)

        val found = brandRepository.findWithFilter(body.p_search.toString())

        val list: List<BrandEntity> = found.toList()

        return list.map { brandList(it) }
    }

    private fun brandList(brand: BrandEntity): BrandResponse{
        return BrandResponse(
            cd_brand = brand.cd_brand!!,
            desc_brand = brand.desc_brand!!
        )
    }
}