package com.nadir.mib.exceptions

data class MessageMapper (
    val message: String,
    val errors: Any?
) {
    fun getMessageClient (): String {
        return this.message.split(";").last().split("[").last().filter { it -> it != ']' }
    }
}
