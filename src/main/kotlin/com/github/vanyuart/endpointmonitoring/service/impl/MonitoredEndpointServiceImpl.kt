package com.github.vanyuart.endpointmonitoring.service.impl

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.repository.MonitoredEndpointRepository
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MonitoredEndpointServiceImpl(
    private val monitoredEndpointRepository: MonitoredEndpointRepository
) : MonitoredEndpointService {

    override fun createMonitoredEndpoint(name: String, url: String, monitoringInterval: Int, owner: User) {
        val monitoredEndpoint = MonitoredEndpoint(
            name = name,
            url = url,
            monitoringInterval = monitoringInterval,
            owner = owner
        )
        monitoredEndpointRepository.save(monitoredEndpoint)
    }

    override fun getEndpointsByUser(user: User): List<MonitoredEndpoint> =
        monitoredEndpointRepository.findAllByOwner(user)

}