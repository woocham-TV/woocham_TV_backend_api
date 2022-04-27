package com.woochamtv.api.error.exception

import com.woochamtv.api.error.ErrorCode

class UserChannelNotFoundException : GlobalException(
    ErrorCode.USER_CHANNEL_NOT_FOUND
)