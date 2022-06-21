package br.project.users.users.exception

class NotFoundException(override val message : String, val errorCode : String) : Exception() {
}