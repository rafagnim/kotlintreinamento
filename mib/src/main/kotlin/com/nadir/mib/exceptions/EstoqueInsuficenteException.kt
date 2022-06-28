package com.nadir.mib.exceptions

import org.springframework.http.HttpStatus

class EstoqueInsuficenteException (override val message : String, val status: Int) : Exception()