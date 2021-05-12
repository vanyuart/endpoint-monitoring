package com.github.vanyuart.endpointmonitoring.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @Column(nullable = false, updatable = false)
    @CreatedDate
    var createdDate: ZonedDateTime = ZonedDateTime.now(),

    @Column
    @LastModifiedDate
    var changedDate: ZonedDateTime? = null,
) : Serializable