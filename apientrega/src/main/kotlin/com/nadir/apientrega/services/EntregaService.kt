package com.nadir.apientrega.services

import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.repositories.EntregaRepository
import org.springframework.stereotype.Service


@Service
class EntregaService (
    private val entregaRepository: EntregaRepository
        ) {

    fun salvar(entrega: Entrega): Entrega? {
        val retorno: Entrega? = entregaRepository?.save(entrega)
        return retorno
    }

    fun findAll(): List<Entrega> {
        return entregaRepository?.findAll() as List<Entrega>
    }

    fun findOne(id: String): Entrega? {
        val retorno = entregaRepository?.findById(id)
        if (retorno != null) {
            return retorno.get()
        } else {
            return null;
        }
    }
}
