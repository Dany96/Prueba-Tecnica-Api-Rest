package com.webservice.webservice.Repository


import com.webservice.webservice.Model.Moneda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MonedaRepository:JpaRepository<Moneda,Long> {

    @Query(value = "SELECT * FROM moneda WHERE valor = ?1 AND dia= ?2", nativeQuery = true)
    fun findByValor(valor: String, dia:String): Optional<Moneda>?

    @Query(value = "SELECT * FROM moneda WHERE sigla = ?1 ORDER BY id DESC LIMIT 5", nativeQuery = true)
    fun findByMoneda(moneda:String): List<Moneda>?
}

