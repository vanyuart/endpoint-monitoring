package com.github.vanyuart.endpointmonitoring.entity

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class MonitoringResult(

    var dateOfCheck: ZonedDateTime,

    var statusCode: Int,

    var payload: String,

    @ManyToOne
    var endpoint: MonitoredEndpoint

) : AbstractEntity()