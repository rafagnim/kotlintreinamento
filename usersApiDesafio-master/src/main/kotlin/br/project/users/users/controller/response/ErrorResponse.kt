package br.project.users.users.controller.response

data class ErrorResponse(
        var httpCode: Int,
        var message: String,
        var errors : List<String>? = null
)
