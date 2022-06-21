package br.project.users.users.service

import br.project.users.users.controller.request.PostUserRequest
import br.project.users.users.entity.User
import br.project.users.users.exception.BadRequestException
import br.project.users.users.exception.NotFoundException
import br.project.users.users.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val bCrypt: BCryptPasswordEncoder
) {
    fun save(request: PostUserRequest) {
        if (userRepository.existsByEmail(request.email))
            throw BadRequestException("Email already exists.", "002")

        request.password = bCrypt.encode(request.password)
        userRepository.save(request.toEntity())
    }

    fun disable(id: Int) {
        val user = userRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id), "001") }
        user.isActive = "N"
        userRepository.save(user)
    }

    fun enable(id: Int) {
        val user = userRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id), "001") }
        user.isActive = "S"
        userRepository.save(user)
    }

    fun getAll(): List<User> {
        return userRepository.findAll().map { it.toGetAll() }
    }

    fun getById(id : Int): User{
        return userRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id), "001") }
    }
}