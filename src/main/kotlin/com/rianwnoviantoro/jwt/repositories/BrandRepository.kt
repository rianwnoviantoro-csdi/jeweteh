package com.rianwnoviantoro.jwt.repositories

import com.rianwnoviantoro.jwt.domains.entities.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

interface BrandRepository: JpaRepository<BrandEntity, UUID> {
    @Query(value = "SELECT b.* FROM brands b WHERE b.desc_brand LIKE %:search%", nativeQuery = true)
    fun findWithFilter(@Param("search") search: String): List<BrandEntity>
}