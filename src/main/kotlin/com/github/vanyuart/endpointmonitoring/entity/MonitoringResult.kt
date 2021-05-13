package com.github.vanyuart.endpointmonitoring.entity

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class MonitoringResult(

    /**
     * Status code will be null if there will be an exception during check
     */
    @Column
    var statusCode: Int?,

    /**
     * Response body or exception message
     */
    @Column(nullable = false)
    var payload: String,

    @Column(nullable = false)
    var dateOfCheck: ZonedDateTime,

    @ManyToOne
    var endpoint: MonitoredEndpoint

) : AbstractEntity()