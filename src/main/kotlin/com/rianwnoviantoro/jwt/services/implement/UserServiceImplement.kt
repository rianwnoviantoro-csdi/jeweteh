package com.rianwnoviantoro.jwt.services.implement

import com.rianwnoviantoro.jwt.domains.dto.requests.SignupRequest
import com.rianwnoviantoro.jwt.domains.entities.UserEntity
import com.rianwnoviantoro.jwt.repositories.UserRepository
import com.rianwnoviantoro.jwt.services.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImplement(private val userRepository: UserRepository): UserService {
    override fun save(body: SignupRequest) {
        val user = UserEntity()

        user.name = body.name
        user.email = body.email
        user.password = body.password

        userRepository.save(user)
    }

    override fun findByEmail(email: String): Optional<UserEntity> {
        return userRepository.findByEmail(email)
    }

    override fun getById(id: UUID): Optional<UserEntity> {
        return userRepository.findById(id)
    }
}