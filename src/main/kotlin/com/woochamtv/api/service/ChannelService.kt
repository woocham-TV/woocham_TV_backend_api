package com.woochamtv.api.service

import com.woochamtv.api.entity.Channel
import com.woochamtv.api.error.exception.UserChannelConflictException
import com.woochamtv.api.payload.ChannelRequest
import com.woochamtv.api.repository.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ChannelService (
    private val imageUploadService: ImageUploadService,
    private val channelRepository: ChannelRepository
) {
    fun makeChannel(request: ChannelRequest, thumbnail: MultipartFile) {
        if (channelRepository.existsById(request.constructor)) throw UserChannelConflictException()
        val url = imageUploadService.uploadImage(thumbnail)
        val channel = Channel(request.constructor, request.title, url)
        channelRepository.save(channel)
    }
}