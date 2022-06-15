package com.nadir.apientrega

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@SpringBootApplication
@EnableMongoRepositories
class ApientregaApplication

fun main(args: Array<String>) {
	runApplication<ApientregaApplication>(*args)
}
