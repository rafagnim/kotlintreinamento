package com.nadir.apientrega.exceptions

class EntityNotFoundException (override val message : String, val errorCode : String) : Exception()