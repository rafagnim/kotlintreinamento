package com.nadir.apiclientes.exceptions

class AuthenticationException(override val message : String, val errorCode : String) : Exception()