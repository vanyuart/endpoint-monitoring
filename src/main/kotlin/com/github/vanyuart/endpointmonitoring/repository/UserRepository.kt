package com.github.vanyuart.endpointmonitoring.repository

import com.github.vanyuart.endpointmonitoring.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsername(username: String): Boolean

}