package com.github.vanyuart.endpointmonitoring.entity

import javax.persistence.*

@Entity
@Table(name = "endpointmonitor_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column
    var email: String? = null,

    @Column
    var accessToken: String? = null,
)