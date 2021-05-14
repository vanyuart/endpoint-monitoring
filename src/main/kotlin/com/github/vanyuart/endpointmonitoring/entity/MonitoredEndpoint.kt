package com.github.vanyuart.endpointmonitoring.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class MonitoredEndpoint(

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var url: String,

    /**
     * In seconds
     */
    @Column(nullable = false)
    var monitoringInterval: Int,

    @Column(nullable = false)
    var nextCheckDate: ZonedDateTime,

    @Column
    var lastCheckDate: ZonedDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var owner: User,

    @OneToMany(mappedBy = "endpoint", cascade = [CascadeType.ALL], orphanRemoval = true)
    var monitoringResults: MutableList<MonitoringResult> = mutableListOf()
) : AbstractEntity()