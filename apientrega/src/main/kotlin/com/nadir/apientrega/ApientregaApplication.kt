package com.nadir.apientrega

import com.nadir.apientrega.repositories.EntregaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
@EnableMongoRepositories
class ApientregaApplication

@Autowired
var groceryItemRepo: EntregaRepository? = null

fun main(args: Array<String>) {
	runApplication<ApientregaApplication>(*args)
}
