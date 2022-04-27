package com.woochamtv.api.controller

import com.woochamtv.api.payload.DeleteChannelRequest
import com.woochamtv.api.payload.MakeChannelRequest
import com.woochamtv.api.service.ChannelService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
class ChannelController (
    private val channelService: ChannelService
) {

    @PostMapping("/channel")
    @ResponseStatus(HttpStatus.CREATED)
    fun makeChannel(@Valid @RequestBody request: MakeChannelRequest, @RequestPart thumbnail: MultipartFile) {
        channelService.makeChannel(request, thumbnail)
    }

    @DeleteMapping("/channel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteChannel(@Valid @RequestBody request: DeleteChannelRequest) {
        channelService.deleteChannel(request.constructor)
    }

}