package com.github.vanyuart.endpointmonitoring.entity

import javax.persistence.*

@Entity
@Table(name = "endpointmonitor_user")
class User(

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column
    var email: String? = null,

    @Column
    var accessToken: String? = null,

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    var endpoints: MutableList<MonitoredEndpoint> = mutableListOf()
) : AbstractEntity()