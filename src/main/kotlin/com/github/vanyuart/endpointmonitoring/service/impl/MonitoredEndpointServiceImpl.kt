package com.github.vanyuart.endpointmonitoring.service.impl

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.exception.NotAllowedException
import com.github.vanyuart.endpointmonitoring.exception.NotFoundException
import com.github.vanyuart.endpointmonitoring.repository.MonitoredEndpointRepository
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime


@Service
@Transactional
class MonitoredEndpointServiceImpl(
    private val monitoredEndpointRepository: MonitoredEndpointRepository
) : MonitoredEndpointService {

    @Transactional(readOnly = true)
    override fun getEndpointsForNextCheck(): List<MonitoredEndpoint> {
        return monitoredEndpointRepository.findAllByNextCheckDateIsNullOrNextCheckDateBefore(ZonedDateTime.now())
    }

    override fun bulkUpdateNextCheckDate(ids: List<Long>, lastCheckDate: ZonedDateTime) {
        val endpoints = monitoredEndpointRepository.findAllById(ids)
        endpoints.forEach { endpoint ->
            endpoint.lastCheckDate = lastCheckDate
            endpoint.nextCheckDate = ZonedDateTime.now().plusSeconds(endpoint.monitoringInterval.toLong())
        }
        monitoredEndpointRepository.saveAll(endpoints)
    }

    @Transactional(readOnly = true)
    override fun getEndpointById(id: Long, user: User): MonitoredEndpoint {
        val endpoint = monitoredEndpointRepository.findByIdOrNull(id)
            ?: throw NotFoundException.create(MonitoredEndpoint::class.simpleName, id)

        if (endpoint.owner.id != user.id) throw NotAllowedException.create(MonitoredEndpoint::javaClass.name, id)

        return endpoint
    }

    @Transactional(readOnly = true)
    override fun getEndpointsByUser(user: User): List<MonitoredEndpoint> =
        monitoredEndpointRepository.findAllByOwner(user)


    override fun createMonitoredEndpoint(name: String, url: String, monitoringInterval: Int, owner: User) {
        val monitoredEndpoint = MonitoredEndpoint(
            name = name,
            url = url,
            monitoringInterval = monitoringInterval,
            owner = owner,
            nextCheckDate = ZonedDateTime.now().plusSeconds(monitoringInterval.toLong())
        )
        monitoredEndpointRepository.save(monitoredEndpoint)
    }

    override fun updateMonitoredEndpoint(id: Long, name: String?, url: String?, monitoringInterval: Int?, owner: User) {
        val endpoint = getEndpointById(id, owner)
        if (name != null) endpoint.name = name
        if (url != null) endpoint.url = url
        if (monitoringInterval != null) {
            // update next check time if monitoringInterval changed
            if (monitoringInterval != endpoint.monitoringInterval) {
                endpoint.nextCheckDate = ZonedDateTime.now().plusSeconds(monitoringInterval.toLong())
            }
            endpoint.monitoringInterval = monitoringInterval
        }
        monitoredEndpointRepository.save(endpoint)
    }

    override fun deleteMonitoredEndpoint(id: Long, user: User) {
        val endpoint = getEndpointById(id, user)
        monitoredEndpointRepository.delete(endpoint)
    }
}