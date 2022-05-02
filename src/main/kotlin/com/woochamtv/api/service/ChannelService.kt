package com.woochamtv.api.service

import com.woochamtv.api.entity.Channel
import com.woochamtv.api.error.exception.UserChannelConflictException
import com.woochamtv.api.error.exception.UserChannelNotFoundException
import com.woochamtv.api.repository.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ChannelService (
    private val imageUploadService: ImageUploadService,
    private val channelRepository: ChannelRepository
) {

    fun makeChannel(constructor: String, title: String, thumbnail: MultipartFile) {
        if (channelRepository.existsById(constructor)) throw UserChannelConflictException()
        val url = imageUploadService.uploadImage(thumbnail)
        val channel = Channel(constructor, title, url)
        channelRepository.save(channel)
    }

    fun deleteChannel(constructor: String) {
        val channel: Channel = channelRepository.findByConstructor(constructor)
            ?: throw UserChannelNotFoundException()
        imageUploadService.deleteFile(channel.thumbnail)
        channelRepository.delete(channel)
    }

}