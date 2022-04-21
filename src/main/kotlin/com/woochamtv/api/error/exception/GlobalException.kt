package com.woochamtv.api.error.exception

import com.woochamtv.api.error.ErrorCode

class GlobalException(val errorCode: ErrorCode, val reason: String) : RuntimeException(
    errorCode.message
)