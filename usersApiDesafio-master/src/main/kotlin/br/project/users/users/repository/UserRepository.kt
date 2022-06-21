package br.project.users.users.repository

import br.project.users.users.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun existsByEmail(email : String) : Boolean
    fun findByEmail(email: String) : User?
}