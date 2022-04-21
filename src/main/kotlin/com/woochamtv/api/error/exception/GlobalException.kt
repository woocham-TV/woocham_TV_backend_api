package com.woochamtv.api.error.exception

import com.woochamtv.api.error.ErrorCode

open class GlobalException(val errorCode: ErrorCode) : RuntimeException(
    errorCode.message
)