package com.nadir.apicompras.services

import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.repositories.CompraRepository
import org.springframework.stereotype.Service

@Service
class CompraService (
    private val compraRepository: CompraRepository
        ) {
    fun salvar(compra: Compra): Compra? {
        val retorno: Compra? = compraRepository?.save(compra)
        return retorno
    }
}