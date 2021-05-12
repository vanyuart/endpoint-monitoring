package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.dto.type.UserDto

/**
 * In context of this task this controller is only used for security testing in [UserControllerTest]
 */
interface UserController {
    fun getUser(): UserDto
}