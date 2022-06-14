package com.nadir.apientrega

import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.repositories.EntregaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class EntregaService {

    @Autowired
    private val entregaRepository: EntregaRepository? = null

    fun salvar(entrega: Entrega): Entrega? {
        return entregaRepository?.save(entrega)
    }

    fun findAll(): List<Entrega> {
        return entregaRepository?.findAll() as List<Entrega>
    }

    fun count(): Long? {
        return entregaRepository?.count()
    }

//    fun findById(id: String?): Entrega {
//        return entregaRepository.findOne(id)
//    }

//    fun delete(id: String?) {
//        entregaRepository.delete(id)
//    }
}