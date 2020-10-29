package com.fanilo.service.api

import com.fanilo.core.exception.HttpErrorException
import com.fanilo.entity.Answer
import com.fanilo.entity.FailureReason
import retrofit2.Response
import java.io.IOException

fun IOException.throwIOException() =
    Answer.Failure(this, this.message.toString(), FailureReason.NETWORK)

fun <T> Response<T>.throwHttpException(): Answer.Failure {
    val error = HttpErrorException(
        this.code(),
        this.errorBody()?.toString()
    )
    return Answer.Failure(error, error.message.toString(), FailureReason.NETWORK)
}