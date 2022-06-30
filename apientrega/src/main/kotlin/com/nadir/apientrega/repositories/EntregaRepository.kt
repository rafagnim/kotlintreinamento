package com.nadir.apientrega.repositories

import com.nadir.apientrega.entities.Entrega
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface EntregaRepository : MongoRepository<Entrega?, UUID?> {
    fun findByIdCliente(idCliente: Long): List<Entrega>
}