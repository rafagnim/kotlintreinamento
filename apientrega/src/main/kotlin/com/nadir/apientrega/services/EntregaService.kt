package com.nadir.apientrega.services

import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.exceptions.EntityNotFoundException
import com.nadir.apientrega.repositories.EntregaRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class EntregaService (
    private val entregaRepository: EntregaRepository
        ) {

    fun salvar(entrega: Entrega): Entrega? {
        val retorno: Entrega = entregaRepository?.save(entrega)
        return retorno
    }

    fun findAll(): List<Entrega> {
        return entregaRepository?.findAll() as List<Entrega>
    }

    fun findOne(id: UUID): Entrega? {
        val retorno = entregaRepository?.findById(id)
        if (retorno != null) {
            return retorno.get()
        } else {
            return null;
        }
    }

    fun getAllByUserId (idCliente: Long): List<Entrega> {
        val retorno: List<Entrega> = entregaRepository.findByIdCliente(idCliente)
        return retorno
    }

    fun getAllByUserIdAndId (idCliente: Long, id: UUID): Entrega {
        val retorno = entregaRepository.findById(id).orElseThrow { EntityNotFoundException("Compra não localizada", "404") }
        if (retorno != null && retorno.idCliente == idCliente) {
            return retorno
        } else {
            throw EntityNotFoundException("Compra não localizada", "404")
        }
    }
}
