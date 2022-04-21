package com.woochamtv.api.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request")
}