package com.nadir.apicompras.repositories

import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.reponses.CompraResponse
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompraRepository: MongoRepository<Compra, UUID> {
    fun findByIdCliente(idCliente: Long): List<CompraResponse>
}