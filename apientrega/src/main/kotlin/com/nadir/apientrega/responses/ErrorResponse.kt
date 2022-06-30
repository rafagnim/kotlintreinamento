package com.nadir.apientrega.responses

data class ErrorResponse(
    var message: String,
    var errors : List<String>? = null
)