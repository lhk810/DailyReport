package com.daily.report

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException


@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ResponseStatusException::class)
    fun handleBadRequestException(ex: ResponseStatusException): Error {
        return Error(ex.status, ex.reason)
    }
}

class Error {
    var status: HttpStatus? = null
    var message: String? = null

    constructor(status: HttpStatus?, message: String?) {
        this.status = status
        this.message = message
    }
}

class TaskNotFoundException(): Throwable() {
}

class TaskAlreadyExistException(): Throwable() {
}