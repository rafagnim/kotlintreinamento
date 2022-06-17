package com.nadir.apicep.repositories

import com.nadir.apicep.entities.Endereco
import com.nadir.apicep.entities.EnderecoId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnderecoRepository : JpaRepository<Endereco, EnderecoId> {
}