package com.woochamtv.api.repository

import com.woochamtv.api.entity.Channel
import org.springframework.data.mongodb.repository.MongoRepository

interface ChannelRepository: MongoRepository<Channel, String>