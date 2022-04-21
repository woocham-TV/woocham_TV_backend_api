package com.woochamtv.api.error

class ErrorResponse(errorCode: ErrorCode, reason: String) {

    val status: Int
    val message: String
    val reason: String

    init {
        status = errorCode.status
        message = errorCode.message
        this.reason = reason
    }

}