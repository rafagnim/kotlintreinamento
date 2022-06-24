package com.nadir.apicompras.reponses

data class ErrorResponse(
    var message: String,
    var errors : List<String>? = null
)