package com.nadir.apicompras.services

import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.exceptions.EntityNotFoundException
import com.nadir.apicompras.reponses.CompraResponse
import com.nadir.apicompras.repositories.CompraRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CompraService (
    private val compraRepository: CompraRepository
        ) {
    fun salvar(compra: Compra): Compra? {
        val retorno: Compra? = compraRepository?.save(compra)
        return retorno
    }

    fun getAllByUserId (idCliente: Long): List<CompraResponse> {
        val retorno: List<CompraResponse> = compraRepository.findByIdCliente(idCliente)
        return retorno
    }

    fun getAllByUserIdAndId (idCliente: Long, id: UUID): CompraResponse {
        val retorno = compraRepository.findById(id).orElseThrow { EntityNotFoundException("Compra não localizada", "404") }
        if (retorno.idCliente == idCliente) {
            return retorno.toResponse()
        } else {
            throw EntityNotFoundException("Compra não localizada", "404")
        }
    }
}
