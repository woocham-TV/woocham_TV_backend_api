package com.woochamtv.api.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.woochamtv.api.error.exception.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.*


@Component
class ImageUploadService (
    private val s3: AmazonS3,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket:String

    private val IMAGE_EXTENSION: List<String> = listOf("jpeg", "png", "jpg")
    private val IMAGE_CONTENT_TYPE: List<String> = listOf("image/png", "image/jpeg")

    fun uploadImage(image:MultipartFile): String {
        val file: File = convert(image)

        val extension: String = file.extension
        val contentType: String? = image.contentType

        if (!IMAGE_EXTENSION.contains(extension) || !IMAGE_CONTENT_TYPE.contains(contentType)) {
            throw BadRequestException()
        }

        val fileName: String = createFileName(extension)
        s3.putObject(bucket, fileName, file)
        Files.delete(file.toPath())
        return getFileUrl(fileName)
    }

    fun deleteFile(fileUrl: String) {
        val fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1)
        s3.deleteObject(DeleteObjectRequest(bucket, fileName))
    }

    private fun getFileUrl(fileName: String): String {
        return s3.getUrl(bucket, fileName).toString()
    }

    private fun convert(file: MultipartFile): File {
        val image = File(System.getProperty("user.dir") + "/" + UUID.randomUUID() + file.originalFilename)
        try {
            file.transferTo(image)
        } catch (e: IOException) {
            throw BadRequestException()
        }
        return image
    }

    private fun createFileName(extension: String): String {
        return "Image_" + UUID.randomUUID() + "." + extension
    }

}