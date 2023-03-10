package com.rianwnoviantoro.jwt.repositories

import com.rianwnoviantoro.jwt.domains.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): Optional<UserEntity>
}