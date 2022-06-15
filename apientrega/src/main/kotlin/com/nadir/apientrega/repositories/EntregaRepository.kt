package com.nadir.apientrega.repositories

import com.nadir.apientrega.entities.Entrega
import org.springframework.data.mongodb.repository.MongoRepository

interface EntregaRepository : MongoRepository<Entrega?, String?> {
}