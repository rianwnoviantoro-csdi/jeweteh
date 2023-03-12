package com.rianwnoviantoro.jwt.services.implement

import com.rianwnoviantoro.jwt.domains.dto.requests.CreateBrandRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.GetBrandRequest
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

    override fun findWithFilter(body: GetBrandRequest): List<BrandEntity> {
        validationUtils.validate(body)

        val found = brandRepository.findWithFilter(body.search.toString())

        println(found)

//        if (!found.isPresent) {
//            throw NotFoundException()
//        }

        return found
    }
}