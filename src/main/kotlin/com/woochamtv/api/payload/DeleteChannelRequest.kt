package com.woochamtv.api.payload

import javax.validation.constraints.NotEmpty

class DeleteChannelRequest (
    @NotEmpty
    val constructor: String
)