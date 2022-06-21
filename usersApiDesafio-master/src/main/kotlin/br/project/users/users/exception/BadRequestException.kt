package br.project.users.users.exception

class BadRequestException(override val message : String, val errorCode : String) : Exception()