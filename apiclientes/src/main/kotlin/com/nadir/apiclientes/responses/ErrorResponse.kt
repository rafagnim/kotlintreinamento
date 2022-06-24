package com.nadir.apiclientes.responses

data class ErrorResponse(
    var message: String,
    var errors : List<String>? = null
)