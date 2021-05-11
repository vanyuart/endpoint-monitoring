package com.github.vanyuart.endpointmonitoring.entity

import javax.persistence.*

@Entity
@Table(name = "endpointmonitor_user")
class User(

    @Column(unique = true, nullable = false)
    var username: String,

    @Column
    var email: String? = null,

    @Column
    var accessToken: String,

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    var endpoints: MutableList<MonitoredEndpoint> = mutableListOf()
) : AbstractEntity()