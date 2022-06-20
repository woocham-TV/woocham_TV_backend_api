package com.woochamtv.api.service

import com.woochamtv.api.dto.ChannelResponse
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

    fun getChannels(): List<ChannelResponse> {
        return channelRepository.findAll().stream()
            .map { channel -> ChannelResponse(channel.constructor, channel.profileName, channel.profileEmoji, channel.title, channel.thumbnail) }
            .toList()
    }

    fun getChannel(constructor: String): ChannelResponse {
        return channelRepository.findByConstructor(constructor)
            ?.let {
                    channel -> ChannelResponse(channel.constructor, channel.profileName, channel.profileEmoji, channel.title, channel.thumbnail)
            }?: throw UserChannelNotFoundException()
    }

    fun makeChannel(constructor: String, profileName: String,
                    profileEmoji: String, title: String, thumbnail: MultipartFile) {
        if (channelRepository.existsById(constructor)) throw UserChannelConflictException()
        val url = imageUploadService.uploadImage(thumbnail)
        val channel = Channel(constructor, profileName, profileEmoji, title, url)
        channelRepository.save(channel)
    }

    fun deleteChannel(constructor: String) {
        val channel: Channel = channelRepository.findByConstructor(constructor)
            ?: throw UserChannelNotFoundException()
        imageUploadService.deleteFile(channel.thumbnail)
        channelRepository.delete(channel)
    }

}