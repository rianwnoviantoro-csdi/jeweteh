package com.rianwnoviantoro.jwt.services.implement

import com.rianwnoviantoro.jwt.domains.dto.requests.SigninRequest
import com.rianwnoviantoro.jwt.domains.dto.requests.SignupRequest
import com.rianwnoviantoro.jwt.domains.entities.UserEntity
import com.rianwnoviantoro.jwt.error.NotFoundException
import com.rianwnoviantoro.jwt.repositories.UserRepository
import com.rianwnoviantoro.jwt.services.UserService
import com.rianwnoviantoro.jwt.utils.ValidationUtils
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImplement(private val userRepository: UserRepository, private val validationUtils: ValidationUtils): UserService {
    override fun save(body: SignupRequest) {
        validationUtils.validate(body)

        val user = UserEntity()

        user.name = body.name
        user.email = body.email
        user.password = body.password

        userRepository.save(user)
    }

    override fun findByEmail(body: SigninRequest): Optional<UserEntity> {
        validationUtils.validate(body)

        val foundUser = userRepository.findByEmail(body.email)

        if (!foundUser.isPresent) {
            throw NotFoundException()
        }

        return foundUser
    }

    override fun getById(id: UUID): Optional<UserEntity> {
        val foundUser = userRepository.findById(id)

        if (!foundUser.isPresent) {
            throw NotFoundException()
        }

        return foundUser
    }
}