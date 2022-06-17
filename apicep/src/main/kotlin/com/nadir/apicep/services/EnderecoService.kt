package com.nadir.apicep.services

import com.nadir.apicep.entities.Endereco
import com.nadir.apicep.entities.EnderecoId
import com.nadir.apicep.repositories.EnderecoRepository
import org.springframework.stereotype.Service

@Service
class EnderecoService (

   private val enderecoRepository: EnderecoRepository

   ) {

    fun geraCEP(enderecoId: EnderecoId): String {
        return "${(10..99).random()}.${(100..999).random()}-${(100..999).random()}"
    }

    fun obterCepPorEndereco (enderecoId: EnderecoId) : String {
        val optional = enderecoRepository.findById(enderecoId)
        if (optional.isEmpty) {
            var cep: String = this.geraCEP(enderecoId)
            enderecoRepository.save(Endereco(enderecoId, cep))
            return cep
        } else {
            return optional.get().cep;
        }
    }
}