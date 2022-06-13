package com.woochamtv.api.controller

import com.woochamtv.api.dto.ChannelResponse
import com.woochamtv.api.service.ChannelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class ChannelController (
    private val channelService: ChannelService
) {

    @GetMapping("/channels")
    fun getChanels(): List<ChannelResponse> {
        return channelService.getChannels()
    }

    @PostMapping("/channel")
    @ResponseStatus(HttpStatus.CREATED)
    fun makeChannel(@RequestParam("constructor") constructor: String,
                    @RequestParam("profile_name") profileName: String,
                    @RequestParam("profile_emoji") profileEmoji: String,
                    @RequestParam("title") title: String,
                    @RequestPart thumbnail: MultipartFile) {
        channelService.makeChannel(constructor, profileName, profileName, title, thumbnail)
    }

    @DeleteMapping("/channel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteChannel(@RequestParam("constructor") constructor: String) {
        channelService.deleteChannel(constructor)
    }

}