package com.woochamtv.api.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Channel(
    @Id
    val constructor: String,
    val title: String,
    val thumbnail: String
)