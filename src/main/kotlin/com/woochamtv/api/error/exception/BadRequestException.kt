package com.woochamtv.api.error.exception

import com.woochamtv.api.error.ErrorCode

class BadRequestException : GlobalException(
    ErrorCode.BAD_REQUEST
)