package com.webservice.webservice.Model

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "data")
data class Data(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        val id        : Long,

        @Column(name = "temperatura")
        var temperature   : String?,

        @Column(name = "humedad")
        var humidity   : String?,

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "hora")
        val hora : Date?)
{
    constructor() : this(0, "", "", Timestamp(Date().time))
}
