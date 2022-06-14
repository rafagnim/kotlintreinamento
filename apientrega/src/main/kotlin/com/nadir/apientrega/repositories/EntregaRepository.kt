package com.nadir.apientrega.repositories


import com.nadir.apientrega.entities.Entrega
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface EntregaRepository : MongoRepository<Entrega?, String?> {
}