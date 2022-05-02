package com.woochamtv.api.error

class ErrorResponse(errorCode: ErrorCode) {

    val status: Int
    val message: String

    init {
        status = errorCode.status
        message = errorCode.message
    }

}