package com.nadir.apiclientes.exceptions

class NotFoundException(override val message : String, val errorCode : String) : Exception() {
}