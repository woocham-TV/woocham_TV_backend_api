package com.woochamtv.api.error.exception

import com.woochamtv.api.error.ErrorCode

class UserChannelConflictException : GlobalException(
    ErrorCode.USER_CHANNEL_CONFLICT
)