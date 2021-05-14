package com.github.vanyuart.endpointmonitoring.controller.impl

import com.github.vanyuart.endpointmonitoring.controller.UserController
import com.github.vanyuart.endpointmonitoring.dto.type.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserControllerImpl : AbstractController(), UserController {

    @GetMapping("current")
    fun getLoggedUser(): UserDto = getLoggedUser().toDTO()
}