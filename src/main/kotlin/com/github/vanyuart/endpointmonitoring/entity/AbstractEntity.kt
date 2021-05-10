package com.github.vanyuart.endpointmonitoring.entity

import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    var createdDate: ZonedDateTime = ZonedDateTime.now(),

    @Column(nullable = false)
    var changedDate: ZonedDateTime? = null,
) : Serializable