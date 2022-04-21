package com.woochamtv.api.error

import com.woochamtv.api.error.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val firstErrorMessage = e.bindingResult.allErrors[0].defaultMessage.toString()
        val response = ErrorResponse(ErrorCode.BAD_REQUEST, firstErrorMessage)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.status))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableExceptionException(e: HttpMessageNotReadableException?): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ErrorCode.BAD_REQUEST, "Request Message Can't Read")
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.status))
    }

    @ExceptionHandler(GlobalException::class)
    protected fun handleGlobalException(e: GlobalException): ResponseEntity<ErrorResponse> {
        val errorCode: ErrorCode = e.errorCode
        val response = ErrorResponse(errorCode, e.reason)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        val response = ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.message.toString())
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.status))
    }

}