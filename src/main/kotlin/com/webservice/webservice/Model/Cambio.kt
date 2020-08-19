package com.webservice.webservice.Model

import javax.persistence.*

@Entity
@Table(name = "cambio")
data class Cambio private constructor(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        val id        : Long,

        @Column(name = "moneda")
        var moneda   : String?,

        @Column(name = "cambio")
        var cambio   : String?,

        @Column(name = "fecha")
        var fecha   : String?)
{
    private constructor() : this(0, "", "", "")
}
