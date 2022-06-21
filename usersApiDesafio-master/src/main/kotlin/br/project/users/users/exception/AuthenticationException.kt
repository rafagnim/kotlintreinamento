package br.project.users.users.exception

class AuthenticationException(override val message : String, val errorCode : String) : Exception()