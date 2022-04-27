package com.woochamtv.api.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    USER_CHANNEL_CONFLICT(409, "User Already Make Channel"),
    USER_CHANNEL_NOT_FOUND(404, "User Channel Not Found")
}