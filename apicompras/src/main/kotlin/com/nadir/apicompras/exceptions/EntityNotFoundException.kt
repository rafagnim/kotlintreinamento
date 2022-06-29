package com.nadir.apicompras.exceptions

class EntityNotFoundException (override val message : String, val errorCode : String) : Exception()