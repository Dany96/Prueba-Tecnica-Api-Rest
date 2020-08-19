package com.webservice.webservice.Repository

import com.webservice.webservice.Model.Cambio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CambioRepository:JpaRepository<Cambio,Long> {

    @Query(value = "SELECT * FROM cambio WHERE moneda = ?1 AND fecha= ?2", nativeQuery = true)
    fun findByMoneda(moneda: String, fecha:String): Cambio?

    @Query(value = "SELECT * FROM cambio WHERE moneda = ?1", nativeQuery = true)
    fun findByAllMoneda(moneda: String):  List<Cambio>?

}

