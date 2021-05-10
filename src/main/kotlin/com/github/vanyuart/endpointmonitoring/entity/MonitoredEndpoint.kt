package com.github.vanyuart.endpointmonitoring.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class MonitoredEndpoint(

    var name: String,

    var url: String,

    /**
     * In seconds
     */
    var monitoredInterval: Int,

    @ManyToOne
    var owner: User,

    @OneToMany(mappedBy = "endpoint", cascade = [CascadeType.ALL], orphanRemoval = true)
    var monitoringResults: MutableList<MonitoringResult> = mutableListOf()
) : AbstractEntity()