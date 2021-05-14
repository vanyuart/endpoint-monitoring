package com.github.vanyuart.endpointmonitoring.controller.impl

import com.github.vanyuart.endpointmonitoring.controller.MonitoredEndpointController
import com.github.vanyuart.endpointmonitoring.dto.CreateMonitoredEndpointReq
import com.github.vanyuart.endpointmonitoring.dto.UpdateMonitoredEndpointReq
import com.github.vanyuart.endpointmonitoring.dto.toDTO
import com.github.vanyuart.endpointmonitoring.dto.type.MonitoredEndpointDto
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("endpoints")
class MonitoredEndpointControllerImpl(
    private val monitoredEndpointService: MonitoredEndpointService,
) : AbstractController(), MonitoredEndpointController {

    @GetMapping("{endpointId}")
    override fun getUserEndpoint(@PathVariable endpointId: Long): MonitoredEndpointDto =
        monitoredEndpointService.getEndpointById(endpointId, getCurrentUser()).toDTO()

    @GetMapping
    override fun getUserEndpoints(): List<MonitoredEndpointDto> =
        monitoredEndpointService.getEndpointsByUser(getCurrentUser()).map { it.toDTO() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createEndpoint(@Valid @RequestBody req: CreateMonitoredEndpointReq) =
        monitoredEndpointService.createMonitoredEndpoint(
            name = req.name,
            url = req.url,
            monitoringInterval = req.monitoringInterval,
            owner = getCurrentUser()
        )

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun updateEndpoint(@Valid @RequestBody req: UpdateMonitoredEndpointReq) =
        monitoredEndpointService.updateMonitoredEndpoint(
            id = req.id,
            name = req.name,
            url = req.url,
            monitoringInterval = req.monitoringInterval,
            owner = getCurrentUser()
        )

    @DeleteMapping("{endpointId}")
    @ResponseStatus(HttpStatus.OK)
    override fun deleteEndpoint(@PathVariable endpointId: Long) =
        monitoredEndpointService.deleteMonitoredEndpoint(endpointId, getCurrentUser())
}