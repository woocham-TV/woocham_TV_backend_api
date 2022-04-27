package com.woochamtv.api.payload

import javax.validation.constraints.NotEmpty

class MakeChannelRequest (
    @NotEmpty
    val constructor: String,
    @NotEmpty
    val title: String
)