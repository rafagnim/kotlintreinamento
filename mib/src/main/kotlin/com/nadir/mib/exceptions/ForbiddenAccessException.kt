package com.nadir.mib.exceptions

import org.springframework.http.HttpStatus

class ForbiddenAccessException (override val message : String, val status: Int) : Exception()