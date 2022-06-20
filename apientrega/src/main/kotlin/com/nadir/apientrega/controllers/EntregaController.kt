package com.nadir.apientrega.controllers

//import com.nadir.apientrega.entities.Compra
import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.services.EntregaService
import com.nadir.apientrega.requests.EntregaRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/entregas")
class EntregaController (
    private val entregaService: EntregaService
        ) {

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun create(@RequestBody @Valid request: EntregaRequest): ResponseEntity<Compra?> {
//        return ResponseEntity.ok(entregaService.salvar(request.toEntregaEntity(null)))
//    }
//
    @GetMapping
    fun getAll(): ResponseEntity<List<Entrega>> {
        return ResponseEntity.ok(entregaService.findAll())
    }
//
//    @GetMapping("/{id}")
//    fun getOne(@PathVariable id : String): ResponseEntity<Compra> {
//        return ResponseEntity.ok(entregaService.findOne(id))
//    }
}