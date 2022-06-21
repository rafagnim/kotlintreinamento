package com.nadir.apiclientes.exceptions

class BadRequestException(override val message : String, val errorCode : String) : Exception()