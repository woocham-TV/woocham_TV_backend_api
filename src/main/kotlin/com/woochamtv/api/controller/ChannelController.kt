package com.woochamtv.api.controller

import com.woochamtv.api.payload.ChannelRequest
import com.woochamtv.api.service.ChannelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class ChannelController (
    private val channelService: ChannelService
) {
    @PostMapping("/channel")
    @ResponseStatus(HttpStatus.CREATED)
    fun makeChannel(@RequestBody request: ChannelRequest, @RequestPart thumbnail: MultipartFile) {
        channelService.makeChannel(request, thumbnail)
    }
}