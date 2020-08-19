package com.webservice.webservice.Model

import javax.persistence.*

@Entity
@Table(name = "moneda")
data class Moneda (
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        val id        : Long,

        @Column(name = "nombre")
        var nombre   : String?,

        @Column(name = "sigla")
        var sigla   : String?,

        @Column(name = "valor")
        var valor   : String?,

        @Column(name = "cambio")
        var cambio   : String?,

        @Column(name = "dia")
        var dia   : String?,

        @Column(name = "resultado")
        var resultado : String?)
{
         constructor() : this(0, "", "", "", "",
                "","")
}
