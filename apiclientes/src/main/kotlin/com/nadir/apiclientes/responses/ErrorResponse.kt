package com.nadir.apiclientes.responses

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var errors : List<String>? = null
)