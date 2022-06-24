package com.nadir.apicompras.exceptions

class EstoqueInsuficenteException (override val message : String, val errorCode : String) : Exception()